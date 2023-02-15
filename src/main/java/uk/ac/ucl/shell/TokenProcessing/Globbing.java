package uk.ac.ucl.shell.TokenProcessing;

import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.Core.SystemDetails;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Globbing is a class that implements the globbing functionality for the shell.
 */
public class Globbing {
    /**
     * ArrayList storing args after expansion
     */
    private static ArrayList<String> globbedArgs;

    /**
     * Method that expands arg recursively
     *
     * @param currentGlobbedPath  Expanded path so far with no \* or \? characters
     * @param unglobbedPath  Path that still needs to be expanded and contains \* or \? characters
     * @param startingPath  Path to return the final path relative to
     */
    private static void globArg(String currentGlobbedPath, String unglobbedPath, String startingPath) {
        if(unglobbedPath == "") {
                String result = Paths.get(startingPath).relativize(Paths.get(currentGlobbedPath)).toString();
                if (startingPath.equals(SystemDetails.fileSeparator)) {
                    result = SystemDetails.fileSeparator + result;
                }
                globbedArgs.add(result);
        }else {
            String globbingPattern, remainingUnglobbedPath;
            if(unglobbedPath.contains(SystemDetails.fileSeparator)) {
                globbingPattern = unglobbedPath.substring(0, unglobbedPath.indexOf(SystemDetails.fileSeparator));
                remainingUnglobbedPath = unglobbedPath.substring(unglobbedPath.indexOf(SystemDetails.fileSeparator) + 1, unglobbedPath.length());
            }  
            else {
                globbingPattern = unglobbedPath;
                remainingUnglobbedPath = "";
            }

            if(globbingPattern.contains("*")) {
                File currentFile = new File(currentGlobbedPath);
                File[] fileArray = currentFile.listFiles();
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + globbingPattern);
                for(File file: fileArray) {
                    if(matcher.matches(Paths.get(file.getName()))) {
                        globArg(currentGlobbedPath + SystemDetails.fileSeparator + file.getName(), remainingUnglobbedPath, startingPath);
                    }
                }
            }
            else {
                globArg(currentGlobbedPath + SystemDetails.fileSeparator + globbingPattern, remainingUnglobbedPath, startingPath);
            }
        }
    }

    /**
     * Method that gets globbed args from a list of args
     *
     * @param appArgs  Args of the app
     * @param ignoreIndex  Index of arg to not subject to globbing, some apps need this
     */
    public static ArrayList<String> globArgs(ArrayList<String> appArgs, int ignoreIndex) {
        globbedArgs = new ArrayList<String>();
        if(appArgs.size() == 0) {
            return globbedArgs;
        }
        
        for(int i = 0; i < appArgs.size(); ++ i) {
            String currentArg = appArgs.get(i);
            if(i == ignoreIndex) {
                globbedArgs.add(currentArg);
                continue;
            }

            int globbedArgsSize = globbedArgs.size();
            if(!currentArg.contains("*")) {
                globbedArgs.add(currentArg);
            }
            else {
                if(currentArg.startsWith(SystemDetails.fileSeparator)) {
                    globArg("", currentArg, SystemDetails.fileSeparator);
                }
                else {
                    globArg(FilePather.getCurrentDirectory(), currentArg, FilePather.getCurrentDirectory());
                }

                if(globbedArgsSize == globbedArgs.size()) {
                    globbedArgs.add(currentArg);
                }
            }
        }
        return globbedArgs;
    }
}
