package com.api.ratelimiter.tokenbucket.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.api.ratelimiter.tokenbucket.constants.AppConstant;
import com.api.ratelimiter.tokenbucket.entities.Employee;
import com.api.ratelimiter.tokenbucket.exceptions.BucketTokenException;
import com.api.ratelimiter.tokenbucket.models.EmployeeModel;
import com.api.ratelimiter.tokenbucket.repositories.EmployeeRepository;
import com.api.ratelimiter.tokenbucket.responses.ResponseMessage;
import com.api.ratelimiter.tokenbucket.service.EmployeeService;
import com.api.ratelimiter.tokenbucket.utilities.CommonUtility;

/**
 * {@link EmployeeServiceImpl} is an implementation class of {@link EmployeeService} which perform CRUD opertaions
 * and perform business logic for the Employee module
 * 
 * @author Saurabh
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private CommonUtility utils;

	
	/**
	 * Method to add or update the Employee info
	 */
	@Override
	public ResponseMessage addUpdateEmployee(EmployeeModel employeeModel) throws BucketTokenException {

		String responseMsg = "Employee Addedd successfully";
		try {
			if (Objects.nonNull(employeeModel)) {
				Employee emp = new Employee();
				if (Objects.nonNull(employeeModel.getId())) {

					Optional<Employee> empOp = empRepo.findById(employeeModel.getId());
					if (empOp.isPresent()) {
						emp = CommonUtility.map(employeeModel, emp);
						emp.setId(empOp.get().getId());
						responseMsg = "Employee info updated successfully";
					} else {
						throw new BucketTokenException(AppConstant.BAD_REQUEST_CODE,
								"Bad Request : No employee found with given details");
					}
				} else {
					emp = CommonUtility.map(employeeModel, emp);
				}
				empRepo.save(emp);
			} else {
				throw new BucketTokenException(AppConstant.BAD_REQUEST_CODE, "Bad Request");
			}
		} catch (BucketTokenException exception) {
			throw new BucketTokenException(exception.getErrorCode(), exception.getErrorMessage());
		} catch (DataAccessException exception) {
			throw new BucketTokenException(AppConstant.BAD_REQUEST_CODE, "Failed save the record");
		} catch (Exception exception) {
			throw new BucketTokenException(AppConstant.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
		return new ResponseMessage(AppConstant.SUCCESS_CODE, responseMsg);
	}

	
	/**
	 * Method to find all employee available in the database
	 */
	@Override
	public List<EmployeeModel> findAllEmployee() throws BucketTokenException {
		List<EmployeeModel> employees = null;
		try {
			List<Employee> empLIst = empRepo.findAll();
			if (Objects.nonNull(empLIst) && !empLIst.isEmpty()) {
				employees = CommonUtility.mapAll(empLIst, EmployeeModel.class);
			} else {
				employees = new ArrayList<>();
			}
		} catch (DataAccessException exception) {
			exception.printStackTrace();
			throw new BucketTokenException(AppConstant.BAD_REQUEST_CODE, "Failed Employee records");
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BucketTokenException(AppConstant.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
		return employees;
	}

	
	/**
	 * Method to find the Employee details based on given Employee Id
	 */
	@Override
	public EmployeeModel findByEmployeeId(String employeeId) throws BucketTokenException {
		EmployeeModel employee = null;
		try {
			if (Objects.isNull(employeeId)) {
				throw new BucketTokenException(AppConstant.BAD_REQUEST_CODE, "Employee Id can't be null");
			} else {
				Optional<Employee> empOp = empRepo.findByEmpId(employeeId);
				if (empOp.isEmpty()) {
					throw new BucketTokenException(AppConstant.BAD_REQUEST_CODE,
							"No employee with given employee Id: " + employeeId);
				} else {
					employee = CommonUtility.map(empOp.get(), EmployeeModel.class);
				}
			}
		} catch (BucketTokenException exception) {

		} catch (DataAccessException exception) {
			throw new BucketTokenException(AppConstant.BAD_REQUEST_CODE, "Failed Employee records");

		} catch (Exception exception) {
			throw new BucketTokenException(AppConstant.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
		return employee;
	}

}
