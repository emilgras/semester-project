/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MockTest;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author emilgras
 */

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {

    @Mock
    MapperMock mapper;
    
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test... " + description.getMethodName() + "\n");
        }
    };

    @Test
    @DisplayName("...")
    public void testGetAllCitiesByBookTitle() {
        String bookTitle = "I am a little butterfly";
        when(mapper.getAllCitiesByBookTitle(bookTitle).size()).thenReturn(5);
        when(mapper.getAllCitiesByBookTitle(bookTitle).get(0).getCityName()).thenReturn("Copenhagen");
        when(mapper.getAllCitiesByBookTitle(bookTitle).get(4).getCityName()).thenReturn("Madrid");
        verify(mapper, times(3)).getAllCitiesByBookTitle(bookTitle);
    }
    
//    @Test
//    @DisplayName("should return all book titles with corresponding authors where the city is mentioned in the book")
//    public void testGetAuthorsByCityName() {
//
//        int expectedResult = 1;
//
//        //List<String> actualResult = mapper.getAuthorsByCityName("copenhagen");
//        //List actualResult = mapper.getBooksMentioningCity(0,0,0);
//        //assertThat(actualResult.size(), is(expectedResult));
//    }
//    
//    @Test
//    @DisplayName("should return all books written by a given author")
//    public void testGetAllBooksWrittenByAuthor() {
//        int expectedResult = 3;
//
//        List actualResult = mapper.getAllBooksWrittenByAuthor("Frank Hansen");
//
//        //assertThat(actualResult.size(), equalTo(expectedResult));
//    }
//
//    
//    @Test
//    @DisplayName("should return an empty array if city is not found in any books")
//    public void testGetBooksByGeoLocation(float latitude, float longtitude) {
//        List<String> expectedResult = new ArrayList<String>();
//
//        List actualResult = mapper.getAuthorsByCityName("");
//
//        assertThat(actualResult, equalTo(expectedResult));
//    }
    
}
