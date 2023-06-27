package com.DesafioTinnova.DesafioTinnova.Q2Test;

import com.DesafioTinnova.DesafioTinnova.questoes.Q2.BubbleSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BubbleSortTest {

    @Test
    @DisplayName("deve ordenar um vetor usando bubble sort")
    public void deveOrdenarUmVetorUsandoBubbleSort() {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.setVetor(new int[]{5, 3, 2, 4, 7, 1, 0, 6});
        bubbleSort.bubbleSort();

        Assertions.assertEquals(0,bubbleSort.getVetorValue(0));
        Assertions.assertEquals(1,bubbleSort.getVetorValue(1));
        Assertions.assertEquals(2,bubbleSort.getVetorValue(2));
        Assertions.assertEquals(3,bubbleSort.getVetorValue(3));
        Assertions.assertEquals(4,bubbleSort.getVetorValue(4));
        Assertions.assertEquals(5,bubbleSort.getVetorValue(5));
        Assertions.assertEquals(6,bubbleSort.getVetorValue(6));
        Assertions.assertEquals(7,bubbleSort.getVetorValue(7));
    }
}
