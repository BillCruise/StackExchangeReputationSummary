package com.billthelizard;

/**
 * Vote types from Stack Exchange Data Explorer.
 * @author Bill Cruise
 */
public enum VoteTypes {

    ACCEPTED_BY_ORIGINATOR(1),
    UPMOD(2),
    DOWNMOD(3),
    OFFENSIVE(4),
    FAVORITE(5),
    CLOSE(6),
    REOPEN(7),
    BOUNTY_START(8),
    BOUNTY_CLOSE(9),
    DELETION(10),
    UNDELETION(11),
    SPAM(12),
    MODERATOR_REVIEW(15),
    APPROVED_EDIT_SUGGESTION(16);
    
    private int value;
    
    VoteTypes(int value) {
        this.value = value;
    }
}