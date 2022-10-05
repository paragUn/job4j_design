package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isNotBlank()
                .isNotNull()
                .doesNotContain("Tetrahedron")
                .endsWith("ere")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isNotBlank()
                .isNotNull()
                .doesNotContain("Sphere")
                .endsWith("ron")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void numberOfVerticesOfTetrahedron() {
        Box box = new Box(4, 6);
        int result = box.getNumberOfVertices();
        assertThat(result).isPositive()
                .isGreaterThan(3)
                .isLessThan(5)
                .isNotZero()
                .isEqualTo(4);
    }

    @Test
    void numberOfVerticesOfUnknown() {
        Box box = new Box(5, -2);
        int result = box.getNumberOfVertices();
        assertThat(result).isNegative()
                .isNotZero()
                .isLessThan(0)
                .isGreaterThan(-3)
                .isEqualTo(-1);
    }

    @Test
    void isExistTetrahedron() {
        Box box = new Box(4, 6);
        boolean result = box.isExist();
        assertThat(result).isNotEqualTo(false)
                .isNotNull()
                .isEqualTo(true);
    }

    @Test
    void isDoesNotExistUnknown() {
        Box box = new Box(5, -2);
        boolean result = box.isExist();
        assertThat(result).isNotEqualTo(true)
                .isNotNull()
                .isEqualTo(false);
    }

    @Test
    void areaOfCube() {
        Box box = new Box(8, 4);
        double result = box.getArea();
        assertThat(result).isNotZero()
                .isGreaterThan(90)
                .isLessThan(100)
                .isEqualTo(96);
    }

    @Test
    void areaOfUnknown() {
        Box box = new Box(5, -2);
        double result = box.getArea();
        assertThat(result).isLessThan(1)
                .isGreaterThan(-2)
                .isEqualTo(0);
    }
}