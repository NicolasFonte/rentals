package nicolas.trial.rest;

import static org.junit.Assert.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import nicolas.trial.model.Rental;

public class RentalResourceTest {

	RentalResource resource;
	@Before
	public void setUp() throws Exception {
		resource = new RentalResource();
	}

	@Test
	public void testFormParametersCanBeConvertedToObjects() {
		
		@SuppressWarnings("unchecked")
		MultivaluedMap<String, String> formParams = Mockito.mock(MultivaluedMap.class);
		
		Mockito.when(formParams.getFirst("form-id")).thenReturn("id");
		Mockito.when(formParams.getFirst("form-country")).thenReturn("country");
		Mockito.when(formParams.getFirst("form-province")).thenReturn("province");
		Mockito.when(formParams.getFirst("form-city")).thenReturn("city");
		Mockito.when(formParams.getFirst("form-roomsNumber")).thenReturn("5");
		Mockito.when(formParams.getFirst("form-dailyPrice")).thenReturn("12.5");
		
		Rental r = resource.formParamsToRental(formParams);
		assertEquals("id", r.getId());
		assertEquals("country", r.getCountry());
		assertEquals("province", r.getProvince());
		assertEquals(5, r.getRoomsNumber());
		assertEquals(12.5, r.getDailyPrice(), 0.0);
		
	}

}
