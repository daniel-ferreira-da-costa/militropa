package unitins.tp1.service.arma;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;

import unitins.tp1.service.file.FileService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmaFileServiceImpl implements FileService {
    //Users/danie/quarkus/images/usuario
    private final String PATH_USER = System.getProperty("user.home") +
            File.separator + "quarkus" +
            File.separator + "images" +
            File.separator + "usuario" + File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10 MB

    @Override
    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException {
        verificarTamanhoImagem(arquivo);

        String mimeType = detectarTipoMime(arquivo);

        if (mimeType == null || !SUPPORTED_MIME_TYPES.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportada: " + mimeType);
        }

        // Criar o diretório caso não exista
        Path diretorio = Paths.get(PATH_USER);
        Files.createDirectories(diretorio);

        // Criar um nome de arquivo aleatório
        String extensao = mimeType.substring(mimeType.lastIndexOf('/') + 1);
        String novoNomeArquivo = UUID.randomUUID() + "." + extensao;

        // Definir o caminho completo do arquivo
        Path filePath = diretorio.resolve(novoNomeArquivo);

        if (filePath.toFile().exists()) {
            throw new IOException("Nome de arquivo já existe. Tente novamente.");
        }

        // Salvar o arquivo
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return filePath.toFile().getName();
    }

    private String detectarTipoMime(byte[] arquivo) throws IOException {
        try (InputStream is = new ByteArrayInputStream(arquivo)) {
            Tika tika = new Tika();
            return tika.detect(is);
        }
    }

    private void verificarTamanhoImagem(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE) {
            throw new IOException("Arquivo maior que 10MB.");
        }
    }

    // Método para obter o arquivo baseado no nome do arquivo
    @Override
    public File obter(String nomeArquivo) {
        File file = new File(PATH_USER + nomeArquivo);
        return file;
    }

    // Método para deletar um arquivo com base no nome do arquivo
    public void deletar(String nomeImagem) {
        // Obtém o caminho do arquivo da imagem
        Path caminhoImagem = Paths.get(PATH_USER + nomeImagem);

        // Tenta excluir o arquivo
        try {
            Files.deleteIfExists(caminhoImagem);
        } catch (IOException e) {
            throw new Error("Não foi possível excluir a imagem", e);
        }
    }
}