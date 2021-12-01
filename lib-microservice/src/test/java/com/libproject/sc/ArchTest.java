package com.libproject.sc;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.libproject.sc");

        noClasses()
            .that()
            .resideInAnyPackage("com.libproject.sc.service..")
            .or()
            .resideInAnyPackage("com.libproject.sc.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.libproject.sc.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
