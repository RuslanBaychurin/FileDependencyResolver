package com.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileDependencyResolver {

    public static void main(String[] args) {

        Path rootPath = Paths.get("root_directory"); //Корневая папка

        try {
            List<Path> textFiles = Files.walk(rootPath)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .collect(Collectors.toList());

            Map<Path, List<Path>> dependencies = new HashMap<>();
            for (Path file : textFiles) {
                List<Path> requiredFiles = parseRequirements(file);
                dependencies.put(file, requiredFiles);
            }

            List<Path> sortedFiles = topologicalSort(dependencies);

            // Объединение содержимого файлов
            StringBuilder combinedContent = new StringBuilder();
            for (Path file : sortedFiles) {
                List<String> lines = Files.readAllLines(file);
                combinedContent.append(String.join("\n", lines)).append("\n");
            }

            // Запись результата в файл
            Path outputFilePath = rootPath.resolve("output/output.txt");
            Files.write(outputFilePath, combinedContent.toString().getBytes());
            System.out.println("Содержимое файлов успешно объединено и записано в " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод для извлечения зависимостей из файла
    private static List<Path> parseRequirements(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        List<Path> requiredFiles = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("*require")) {
                String pathString = line.substring(line.indexOf('‘') + 1, line.indexOf('’'));
                requiredFiles.add(file.getParent().resolve(pathString));
            }
        }
        return requiredFiles;
    }

    // Метод для топологической сортировки файлов
    private static List<Path> topologicalSort(Map<Path, List<Path>> dependencies) throws Exception {
        List<Path> sorted = new ArrayList<>();
        Set<Path> visited = new HashSet<>();
        Set<Path> visiting = new HashSet<>();

        for (Path file : dependencies.keySet()) {
            visit(file, dependencies, visited, visiting, sorted);
        }

        return sorted;
    }

    private static void visit(Path file, Map<Path, List<Path>> dependencies, Set<Path> visited, Set<Path> visiting, List<Path> sorted) throws Exception {
        if (visiting.contains(file)) {
            throw new Exception("Обнаружена циклическая зависимость: " + file);
        }
        if (!visited.contains(file)) {
            visiting.add(file);
            for (Path dep : dependencies.get(file)) {
                visit(dep, dependencies, visited, visiting, sorted);
            }
            visiting.remove(file);
            visited.add(file);
            sorted.add(file);
        }
    }
}
