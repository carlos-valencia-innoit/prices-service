package com.challenge.pricesservice;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class HexagonalArchitectureTest {

    private static final String DOMAIN_PACKAGE = "com.challenge.pricesservice.domain..";
    private static final String APPLICATION_PACKAGE = "com.challenge.pricesservice.application..";
    private static final String INFRASTRUCTURE_PACKAGE = "com.challenge.pricesservice.infrastructure..";
    private static final String BASE_PACKAGE = "com.challenge.pricesservice";

    private final JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages(BASE_PACKAGE);

    @Test
    void domainShouldNotDependOnOtherLayers() {
        ArchRule rule = noClasses()
                .that().resideInAPackage(DOMAIN_PACKAGE)
                .should().dependOnClassesThat()
                .resideInAnyPackage(APPLICATION_PACKAGE, INFRASTRUCTURE_PACKAGE);

        rule.check(importedClasses);
    }

    @Test
    void applicationShouldNotDependOnInfrastructure() {
        ArchRule rule = noClasses()
                .that().resideInAPackage(APPLICATION_PACKAGE)
                .should().dependOnClassesThat()
                .resideInAPackage(INFRASTRUCTURE_PACKAGE);

        rule.check(importedClasses);
    }
}
