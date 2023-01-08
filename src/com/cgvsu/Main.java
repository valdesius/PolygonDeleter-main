package com.cgvsu;

import com.cgvsu.Writer.ObjWriter;
import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.polygonDeleter.PolygonDeleter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        Path fileName = Path.of("cube.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        PolygonDeleter.deletePolygon(model, 12);
        System.out.println();

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        //запись файла
        String filePath = "text5.obj";
        try {
            System.out.println("Создаём файл");
            ObjWriter.createObjFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(filePath);
        try {
            System.out.println("Запись в файл");
            ObjWriter.writeToFile(model, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Модель сохранена в файл");
    }
}
