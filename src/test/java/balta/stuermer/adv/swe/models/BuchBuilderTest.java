package balta.stuermer.adv.swe.models;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class BuchBuilderTest {
    @Test
    public void testGetAttributTitel() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel");
        // Act
        Object ergebnis = builder.getAttribut("Titel");
        // Assert
        Assertions.assertThat(ergebnis).isInstanceOf(String.class);
        Assertions.assertThat(ergebnis).isEqualTo("Test");
    }
    
    @Test
    public void testGetAttributUntertitel() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel");
        // Act
        Object ergebnis = builder.getAttribut("Untertitel");
        // Assert
        Assertions.assertThat(ergebnis).isInstanceOf(String.class);
        Assertions.assertThat(ergebnis).isEqualTo("Untertitel");
    }

    @Test
    public void testGetAttributSeiten() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel")
                .setSeiten(100);
        // Act
        Object ergebnis = builder.getAttribut("Seiten");
        // Assert
        Assertions.assertThat(ergebnis).isInstanceOf(String.class);
        Assertions.assertThat(ergebnis).isEqualTo("100");
    }

    @Test
    public void testGetAttributAutoren() {
        // Arrange
        Autor autor = new AutorBuilder().setId("id").setName("Butor").createAutor();
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel")
        .setAutoren(Collections.singletonList(autor));
        // Act
        Object ergebnis = builder.getAttribut("Autor*Innen");
        // Assert
        Assertions.assertThat(ergebnis).isInstanceOf(List.class);
        Assertions.assertThat(ergebnis).isEqualTo(Collections.singletonList(autor));
    }

    @Test
    public void testGetAttributVerlag() {
        // Arrange
        Verlag verlag = new VerlagBuilder().setId("id").setName("TestVerlag").createVerlag();
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel")
                .setVerlag(verlag);
        // Act
        Object ergebnis = builder.getAttribut("Verlag");
        // Assert
        Assertions.assertThat(ergebnis).isInstanceOf(Verlag.class);
        Assertions.assertThat(ergebnis).isEqualTo(verlag);
    }

    @Test
    public void testGetAttributZustand() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel")
                .setZustand(Zustand.MUMIE);
        // Act
        Object ergebnis = builder.getAttribut("Zustand");
        // Assert
        Assertions.assertThat(ergebnis).isInstanceOf(Zustand.class);
        Assertions.assertThat(ergebnis).isEqualTo(Zustand.MUMIE);
    }

    @Test
    public void testGetAttributNonsense() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel");
        // Act
        Object ergebnis = builder.getAttribut("keysmash#######");
        // Assert
        Assertions.assertThat(ergebnis).isNull();
    }

    @Test
    public void testSetAttributTitel() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel");
        // Act
        builder.setAttribut("Titel", "neuer Titel");
        // Assert
        Assertions.assertThat(builder.createBuch().getTitel()).isEqualTo("neuer Titel");
    }

    @Test
    public void testSetAttributUntertitel() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel");
        // Act
        builder.setAttribut("Untertitel", "neuer Untertitel");
        // Assert
        Assertions.assertThat(builder.createBuch().getUntertitel()).isEqualTo("neuer Untertitel");
    }

    @Test
    public void testSetAttributSeiten() {
        // Arrange
        BuchBuilder builder = new BuchBuilder();
        builder.setTitel("Test")
                .setUntertitel("Untertitel");
        // Act
        builder.setAttribut("Seiten", 100);
        // Assert
        Assertions.assertThat(builder.createBuch().getSeiten()).isEqualTo(100);
    }
}
