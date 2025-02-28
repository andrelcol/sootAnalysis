package com.analise.seguranca;

import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;

public class TestFileEncoding {
    public static void main(String[] args) {
        try {
            Path filePath = Paths.get("src/main/resources/heuristicReport.mtl");
            String content = Files.readString(filePath, StandardCharsets.UTF_8);
            System.out.println("📄 Conteúdo do arquivo:");
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
