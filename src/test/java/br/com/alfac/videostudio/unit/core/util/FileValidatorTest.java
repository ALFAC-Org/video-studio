package br.com.alfac.videostudio.unit.core.util;

import br.com.alfac.videostudio.core.application.util.FileValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileValidatorTest {
    private final FileValidator fileValidator = new FileValidator();

    @Test
    public void testIsMp4File_withValidMp4File() {
        byte[] validMp4File = new byte[] {
            0x00, 0x00, 0x00, 0x18,  // Tamanho do box 'ftyp'
            0x66, 0x74, 0x79, 0x70,  // 'ftyp' signature
            0x6D, 0x70, 0x34, 0x32,  // Tipo do arquivo: 'mp42'
            0x00, 0x00, 0x00, 0x00,  // Flags
            0x6D, 0x70, 0x34, 0x31,  // Compatibilidade: 'mp41'
            0x6D, 0x70, 0x34, 0x32,  // Compatibilidade: 'mp42'
            0x00, 0x00, 0x00, 0x01,  // Dados válidos
            0x00, 0x00, 0x00, 0x02   // Dados válidos
        };
        assertTrue(fileValidator.isMp4File(validMp4File));
    }

    @Test
    public void testIsMp4File_withInvalidFile() {
        byte[] invalidFile = new byte[]{ /* bytes representing an invalid file */ };
        assertFalse(fileValidator.isMp4File(invalidFile));
    }

    @Test
    public void testIsMp4File_withNullFile() {
        assertFalse(fileValidator.isMp4File(null));
    }

    @Test
    public void testIsMp4File_withEmptyFile() {
        assertFalse(fileValidator.isMp4File(new byte[0]));
    }
}
