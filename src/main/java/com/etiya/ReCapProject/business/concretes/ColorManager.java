package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.ReCapProject.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.ColorSearchListDto;
import com.etiya.ReCapProject.business.requests.colorRequests.CreateColorRequest;
import com.etiya.ReCapProject.business.requests.colorRequests.DeleteColorRequest;
import com.etiya.ReCapProject.business.requests.colorRequests.UpdateColorRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.ReCapProject.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		super();
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ColorSearchListDto>> getAll() {
		List<Color> result = this.colorDao.findAll();
		List<ColorSearchListDto> response = result.stream()
				.map(color -> modelMapperService.forDto().map(color, ColorSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ColorSearchListDto>>(response, Messages.COLORLIST);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Result result = BusinessRules.run(checkIfColorNameExists(createColorRequest.getColorName()));
		if (result != null) {
			return result;
		}
		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(Messages.COLORADD);
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Result result = BusinessRules.run(checkIfColorIdExists(deleteColorRequest.getId()));
		if (result != null) {
			return result;
		}
		Color color = modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		this.colorDao.delete(color);
		return new SuccessResult(Messages.COLORDELETE);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Result result = BusinessRules.run(checkIfColorIdExists(updateColorRequest.getId()), checkIfColorNameExists(updateColorRequest.getColorName()));
		if (result != null) {
			return result;
		}
		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(Messages.COLORUPDATE);
	}

	@Override
	public DataResult<ColorSearchListDto> getByColorId(int colorId) {
		var existsResult = this.colorDao.existsById(colorId);
		if (!existsResult){
			return new ErrorDataResult<ColorSearchListDto>(Messages.COLORNOTFOUND,null);
		}
		Color color = this.colorDao.findById(colorId).get();
		ColorSearchListDto response = modelMapperService.forDto().map(color, ColorSearchListDto.class);
		return new SuccessDataResult<ColorSearchListDto>(response, Messages.COLORFOUND);
	}
	
	@Override
	public Result checkIfColorIdExists(int colorId) {
		var result = this.colorDao.existsById(colorId);
		if (!result) {
			return new ErrorResult(Messages.COLORNOTFOUND);
		}
		return new SuccessResult();
	}

	private Result checkIfColorNameExists(String colorName) {
		var result = this.colorDao.existsByColorName(colorName);
		if (result) {

			return new ErrorResult(Messages.COLORNAMEERROR);
		}
		return new SuccessResult();
	}

}
