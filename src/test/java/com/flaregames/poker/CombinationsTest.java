package com.flaregames.poker;

import com.google.common.collect.Iterators;

import org.apache.commons.math3.util.Combinations;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CombinationsTest {

  @Test
  public void generateCombinationsTest() {

    Assertions.assertThat(Iterators.size(new Combinations(52, 5).iterator())).isEqualTo(2598960);
  }
}
