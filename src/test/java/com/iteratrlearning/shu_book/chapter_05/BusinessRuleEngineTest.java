package com.iteratrlearning.shu_book.chapter_05;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessRuleEngineTest {
    @Test
    public void shouldHaveNoRulesInitially(){
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        assertEquals(0, businessRuleEngine.count());
    }

    @Test
    public void shouldAddTwoActions(){
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addAction(facts -> {
            final String jobTitle = facts.getFacts("jobTitle");
            if("CEO".equals(jobTitle)){
                final String name = facts.getFacts("name");
                System.out.println(name);
            }
        });
        businessRuleEngine.addAction(facts -> {
            var jobTitle = facts.getFacts("jobTitle");
            if("CEO".equals(jobTitle)){
                var name = facts.getFacts("name");
                System.out.println(name);
            }
        });

        assertEquals(2, businessRuleEngine.count());
    }

    @Test
    public void shouldExecuteOneAction(){
        //given
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
        final Action mockAction = mock(Action.class);

        //when
        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        //then
        verify(mockAction).perform(mockFacts);
    }

    @Test
    public void shouldPerformAnActionWithFacts(){
        //given
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        //when
        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        //then
        verify(mockAction).perform(mockFacts);

    }

    @Test
    public void calcForecastedAmountWithFacts(){
        //given
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        //when
        businessRuleEngine.addAction(facts -> {
            var forecastedAmount = 0.0;
            var dealStage = Stage.valueOf(facts.getFacts("stage"));
            var amount = Double.parseDouble(facts.getFacts("amount"));
            switch (dealStage){
                case LEAD:
                    forecastedAmount = amount * 0.2; break;
                case EVALUATING:
                    forecastedAmount = amount * 0.5; break;
                case INTERESTED:
                    forecastedAmount = amount * 0.8; break;
                case CLOSE:
                    forecastedAmount = amount; break;
            }
            facts.addFacts("forecastedAmount", String.valueOf(forecastedAmount));
        });

        //then
        verify(mockAction).perform(mockFacts);

    }
}
