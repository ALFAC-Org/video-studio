import java.io.FileOutputStream;
import java.io.IOException;

public class CorruptedMp4File {

    public static void main(String[] args) {
        // Array de bytes representando um arquivo MP4 corrompido
        byte[] corruptedMp4Bytes = new byte[] {
            0x00, 0x00, 0x00, 0x18,  // Tamanho do box 'ftyp'
            0x66, 0x74, 0x79, 0x70,  // 'ftyp' signature
            0x6D, 0x70, 0x34, 0x32,  // Tipo do arquivo: 'mp42'
            0x00, 0x00, 0x00, 0x00,  // Flags
            0x6D, 0x70, 0x34, 0x31,  // Compatibilidade: 'mp41'
            0x6D, 0x70, 0x34, 0x32,  // Compatibilidade: 'mp42'
            0x00, 0x00, 0x00, 0x00,  // Dados corrompidos
            0x00, 0x00, 0x00, 0x00   // Dados corrompidos
        };

        // Nome do arquivo de saída
        String fileName = "corrupted.mp4";

        // Escreve os bytes no arquivo
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(corruptedMp4Bytes);
            System.out.println("Arquivo MP4 corrompido criado com sucesso: " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo MP4 corrompido: " + e.getMessage());
        }
    }
}

// javac CorruptedMp4File.java
// java CorruptedMp4File