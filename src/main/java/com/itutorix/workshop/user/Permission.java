package com.itutorix.workshop.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    PARTICIPANT_READ("participant:read"),
    PARTICIPANT_UPDATE("participant:update"),
    PARTICIPANT_CREATE("participant:create"),
    PARTICIPANT_DELETE("participant:delete"),
    JURY_READ("jury:read"),
    JURY_UPDATE("jury:update"),
    JURY_CREATE("jury:create"),
    JURY_DELETE("jury:delete"),
    ORGANIZER_READ("organizer:read"),
    ORGANIZER_UPDATE("organizer:update"),
    ORGANIZER_CREATE("organizer:create"),
    ORGANIZER_DELETE("organizer:delete");

    private final String permission;
}
