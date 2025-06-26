package com.api.ratelimiter.tokenbucket.utilities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * {@link CommonUtility} contains utilities method which will be used across
 * application
 * 
 * @author Saurabh
 *
 */
@Component
public class CommonUtility {

	public static ModelMapper mapper;

	{
		mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
	}

	/**
	 * Method to convert entity to model based on type of destination class given
	 * model
	 * 
	 * @param <D>
	 * @param <T>
	 * @param sourceClass
	 * @param outclassType
	 * @return
	 */
	public static <D, T> D map(final T sourceClass, Class<D> outclassType) {
		return mapper.map(sourceClass, outclassType);
	}

	/**
	 * Method to convert list of Entity to List of model and vice-versa
	 * 
	 * @param <D>
	 * @param <T>
	 * @param objectList
	 * @param destinationType
	 * @return
	 */
	public static <D, T> List<D> mapAll(final Collection<T> objectList, Class<D> destinationType) {
		return objectList.stream().map(object -> map(object, destinationType)).collect(Collectors.toList());
	}

	/**
	 * Method to convert Entity to model and vice-versa
	 * 
	 * @param <S>
	 * @param <D>
	 * @param source
	 * @param destination
	 * @return
	 */
	public static <S, D> D map(final S source, D destination) {
		mapper.map(source, destination);
		return destination;
	}

	/**
	 * Method to get the response from Page<T> which given by the repositories
	 * 
	 * @param <T>
	 * @param page
	 * @param responseType
	 * @return
	 */
	/*public static <T> ResponseModel<T> getResponseModel(Page<?> page, T responseType) {
		ResponseModel<T> response = new ResponseModel<>();

		response.setContent((List<T>) CommonUtility.mapAll(page.getContent(), responseType.getClass()));
		response.setPageNumber(page.getPageable().getPageNumber());
		response.setPageSize(page.getPageable().getPageSize());
		response.setTotalElement(page.getTotalPages());
		response.setTotalElement(page.getTotalElements());
		return response;

	}*/

	/**
	 * Method to return the pagebale form the {@link RequestModel}
	 * 
	 * @param request
	 * @return
	 */
	/*public static Pageable getPageableFromRequest(RequestModel request) {
		return PageRequest.of(request.getPageNumber(), request.getPageSize(),
				Objects.nonNull(request.getSort()) ? request.getSort() : Sort.unsorted());
	}*/

	/**
	 * Method to find the number of days between two days
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int getDaysBetweenTwoDates(Date secondDate, Date firstDate) {
		long diffInMillis = Math.abs(secondDate.getTime() - firstDate.getTime());
		int bookingdays = (int) (diffInMillis / (1000 * 60 * 60 * 24)) + 1;
		return bookingdays;
	}

}

