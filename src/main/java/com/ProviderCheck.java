package com;

import jakarta.persistence.spi.PersistenceProviderResolverHolder;

public class ProviderCheck {
    public static void main(String[] args) {
        System.out.println("Available providers: " +
                PersistenceProviderResolverHolder.getPersistenceProviderResolver()
                        .getPersistenceProviders());
    }
}