package org.khasanof.forkJoinFramework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 12:00 PM
 * <br/>
 * Package: org.khasanof.forkJoinFramework
 */
public class FolderProcessor extends RecursiveTask<List<String>> {

    //This attribute will store the full path of the folder this task is going to process.
    private final String path;

    //This attribute will store the name of the extension of the files this
    // task is going to look for.
    private final String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    //Implement the compute() method. As you parameterized the RecursiveTask class with the List<String> type,
    //this method has to return an object of that type.
    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected List<String> compute() {

        //List to store the names of the files stored in the folder.
        List<String> list = new ArrayList<>();

        //FolderProcessor tasks to store the subtasks that are going to process the subfolders stored in the folder
        List<FolderProcessor> tasks = new ArrayList<>();

        //Get the content of the folder.
        File file = new File(path);

        File[] files = file.listFiles();
        //For each element in the folder, if there is a subfolder, create a new FolderProcessor object
        //and execute it asynchronously using the fork() method.
        if (files != null) {

            for (File value : files) {
                if (value.isDirectory()) {
                    FolderProcessor processor = new FolderProcessor(value.getAbsolutePath(), extension);
                    processor.fork();
                    tasks.add(processor);
                }


                //Otherwise, compare the extension of the file with the extension you are looking for using the checkFile() method
                //and, if they are equal, store the full path of the file in the list of strings declared earlier.
                else {
                    if (checkFile(value.getName())) {
                        list.add(value.getAbsolutePath());
                    }
                }
            }
        }

        //If the list of the FolderProcessor subtasks has more than 50 elements,
        //write a message to the console to indicate this circumstance.
        if (tasks.size() > 50) {
            System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
        }

        //add to the list of files the results returned by the subtasks launched by this task.
        addResultsFromTasks(list, tasks);
        return list;
    }

    //For each task stored in the list of tasks, call the join() method that
    // will wait for its finalization and then will return the result of the task.
    //Add that result to the list of strings using the addAll() method.
    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor task : tasks) {
            list.addAll(task.join());
        }
    }

    //This method compares if the name of a file passed as a parameter ends with the extension you are looking for.
    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
