package com.ensta.librarymanager.utils;

public enum Abonnement {
    NULL(0), BASIC(2), PREMIUM(5), VIP(20);

    public final int capaciteEmprunt;

    private Abonnement(int capaciteEmprunt){
        this.capaciteEmprunt = capaciteEmprunt;
    }
}