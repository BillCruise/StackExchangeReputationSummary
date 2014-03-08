package com.billthelizard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Parse a Stack Exchange /reputation report and create a summary.
 * @author Bill Cruise
 */
public class ReputationSummary  {
    
    public static void main( String[] args ) {
        
        int myAnswerAccepted = 0;
        int acceptedByMe = 0;
        int questionRep = 0;
        int answerRep = 0;
        int bounties = 0;
        int spam = 0;
        int suggestedEdits = 0;
        int myDownvotes = 0;
        int uncategorized = 0;
        
        File f = new File("resources/reputation-report.txt");
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            
            String line = br.readLine();
            
            while (line != null) {
                if (line.startsWith(" ")) { // rep change
                    String[] tokens = line.split("\\s+");
                    VoteTypes voteType = VoteTypes.values()[Integer.parseInt(tokens[1]) - 1];
                    int postId = Integer.parseInt(tokens[2]);
                    int repChange = Integer.parseInt(tokens[3].replaceAll("[\\[()\\]]", ""));
                    
                    switch (voteType) {
                    case ACCEPTED_BY_ORIGINATOR:
                        if (repChange == 15) {
                            myAnswerAccepted += repChange;
                        }
                        else {
                            acceptedByMe += repChange;
                        }
                        break;
                    case UPMOD:
                        if (repChange == 5) {
                            questionRep += repChange;
                        }
                        else {
                            answerRep += repChange;
                        }
                        break;
                    case DOWNMOD:
                        if (repChange == -1) {
                            myDownvotes--;
                        }
                        else {
                            answerRep += repChange;
                        }
                        break;
                    case BOUNTY_START:
                    case BOUNTY_CLOSE:
                        bounties += repChange;
                        break;
                    case SPAM:
                        spam += repChange;
                        break;
                    case APPROVED_EDIT_SUGGESTION:
                        suggestedEdits += repChange;
                    default:
                        uncategorized += repChange;
                    }
                }
                else if (line.startsWith("--")) { // daily summary
                    
                }
                else {
                    System.out.println(line);
                }
                
                line = br.readLine();
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("My answer was accepted: " + myAnswerAccepted);
        System.out.println("I accepted an answer: " + acceptedByMe);
        System.out.println("Reputation from questions asked: " + questionRep);
        System.out.println("Reputation from answers: " + answerRep);
        System.out.println("Net reputation from bounties: " + bounties);
        System.out.println("Reputation lost due to spam flags: " + spam);
        System.out.println("Reputation from suggested edits: " + suggestedEdits);
        System.out.println("Reputation lost by downvoting: " + myDownvotes);
        System.out.println("Uncategorized: " + uncategorized);
    }
}
