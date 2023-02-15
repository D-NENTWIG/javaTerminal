package uk.ac.ucl.shell.AppCalls;

import uk.ac.ucl.shell.Core.ShellException;
import uk.ac.ucl.shell.FileUtils.FilePather;
import uk.ac.ucl.shell.FileUtils.FileWriter;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Cut is a class that implements the cut command by extending App. 
 */
public class Cut extends App { 
    /**
     * Constructor for Cut 
     */
    Cut(InputStream in, OutputStream out, ArrayList<String> args) {
        super(in, out, args); 
    }

    /**
     * Method that performs basic checks on the app's args
     *
     * @throws  ShellException   If the args are invalid
     */
    public void checkAppArgs() throws ShellException { 
        ArrayList<String> appArgs = this.getAppArgs(); 
        if (appArgs.isEmpty() && this.getIn() == null) { 
            throw new ShellException("cut: missing input"); 
        }
        if (appArgs.size() > 3) { 
            throw new ShellException("cut: too many args"); 
        }

        if (!appArgs.get(0).equals("-b") || appArgs.get(1).equals("-b")) { 
            throw new ShellException("cut: incorrect option input"); 
        }
    }

    /**
     * Method that runs the app's functionality with the given args 
     *
     * @throws  ShellException   If the path is invalid or if could not write to the output file 
     */
    public void run() throws ShellException { 
        ArrayList<String> appArgs = this.getAppArgs(); 
        OutputStream out = this.getOut(); 
        String currentDirectory = FilePather.getCurrentDirectory(); 
        FileWriter writer = new FileWriter(out); 
 
        // Split byte positions into args 
        String[] args = appArgs.get(1).split(","); 
        for (int i = 0; i != args.length; ++i) { 
            if (!Pattern.matches("[0-9]*-*[0-9]*", args[i])) { 
                // Check args are 1, 4-7, -5 or 6-. 
                throw new ShellException("cut: incorrect args"); 
            }
        }
        // Find file source 
        Scanner scanner; 
        if (appArgs.size() == 2) { 
            scanner = FileWriter.getScanner(this.getIn()); 
        } else { 
            Path newFilePath = Paths.get(currentDirectory + File.separatorChar + appArgs.get(2)); 
            scanner = FileWriter.getScanner(newFilePath); 
        }
 
        // Parse cut args and represent all as intervals in an arrayList of arrayLists 
        //[0-9]- represented as arrayLists of length 1 
        ArrayList<ArrayList<Integer>> intervals = new ArrayList<ArrayList<Integer>>(); 
        for (int j = 0; j < args.length; j++) { 
            char[] argchars = args[j].toCharArray(); 
            ArrayList<Integer> interval = new ArrayList<Integer>(); 

            if (argchars.length == 1) { 
                interval.add(Character.getNumericValue(argchars[0] - 1)); 
                interval.add(Character.getNumericValue(argchars[0])); 
            }else { 
                for (int i = 0; i < argchars.length; i++) { 
                    if (argchars[i] == '-') { 
                        if (i == 0) { 
                            interval.add(Character.getNumericValue(0)); 
                            interval.add(Character.getNumericValue(argchars[i + 1])); 
                        } else if (i == argchars.length - 1) { 
                            interval.add(Character.getNumericValue(argchars[i - 1] - 1)); 
                        } else { 
                            interval.add(Character.getNumericValue(argchars[i - 1] - 1)); 
                            interval.add(Character.getNumericValue(argchars[i + 1])); 
                        }
                        break;
                    }
                }
            }
            if(interval.size() == 1 || interval.get(0) < interval.get(1)){ 
                if (interval.get(0) < 0) { 
                    interval.set(0, 0); 
                } 
                intervals.add(interval); 
            }
        }

        // Merge all overlapping intervals 
        ArrayList<ArrayList<Integer>> mergedIntervals = mergeIntervals(intervals); 

        // Cut file using the merged intervals array we made 
        writer.transcribe(scanner, (line) -> { 
            String newLine = ""; 
            for (int i = 0; i < mergedIntervals.size(); i++) { 
                if (mergedIntervals.get(i).get(0) < line.length()) { 
                    if (mergedIntervals.get(i).size() == 1 || mergedIntervals.get(i).get(1) > line.length()) { 
                        newLine += line.substring(mergedIntervals.get(i).get(0)); 
                    } else { 
                        newLine += line.substring(mergedIntervals.get(i).get(0), mergedIntervals.get(i).get(1)); 
                    }
                }
            }
            return newLine;
        });

    }

    public static ArrayList<ArrayList<Integer>> mergeIntervals(ArrayList<ArrayList<Integer>> intervals) {
        // Make sure intervals not empty   
        if (intervals.isEmpty()) {
            return intervals; 
        }

        ArrayList<ArrayList<Integer>> newIntervals = new ArrayList<ArrayList<Integer>>(); 
        newIntervals.addAll(intervals); 

        for (int i = 0; i < newIntervals.size(); i++) { 
            for (int j = i + 1; j < newIntervals.size(); j++) { 
                if (newIntervals.get(i).get(0) > (newIntervals.get(j).get(0))) {
                    ArrayList temp = newIntervals.get(i);  
                    newIntervals.set(i, newIntervals.get(j));
                    newIntervals.set(j, temp); 
                }
            }
        }

        for (int i = 0; i < newIntervals.size() - 1; i++) {
            if (newIntervals.get(i).size() == 1){ 
                newIntervals.subList(i+1, newIntervals.size()).clear(); 
                break; 
            }
            if (newIntervals.get(i).get(1) >= newIntervals.get(i+1).get(0)){ 
                newIntervals.get(i+1).set(0, newIntervals.get(i).get(0)); 
                if (newIntervals.get(i).size() == 1 || newIntervals.get(i+1).size() == 1){ 
                    ArrayList<Integer> tempInterval = new ArrayList<Integer>(); 
                    tempInterval.add(newIntervals.get(i).get(0)); 
                    newIntervals.set(i, tempInterval); 
                    newIntervals.subList(i+1, newIntervals.size()).clear(); 
                    break;
                }else{
                    newIntervals.get(i + 1).set(1, Math.max(newIntervals.get(i).get(1), newIntervals.get(i+1).get(1))); 
                }
                newIntervals.remove(i); 
                i--; 
            }
        }
        return newIntervals;
    }
}
