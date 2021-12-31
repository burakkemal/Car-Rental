package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.ColorSearchListDto;
import com.etiya.ReCapProject.business.requests.colorRequests.CreateColorRequest;
import com.etiya.ReCapProject.business.requests.colorRequests.DeleteColorRequest;
import com.etiya.ReCapProject.business.requests.colorRequests.UpdateColorRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface ColorService {
	DataResult<List<ColorSearchListDto>> getAll();
	Result add(CreateColorRequest createColorRequest);
	Result delete(DeleteColorRequest deleteColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	DataResult<ColorSearchListDto> getByColorId(int colorId);
	Result checkIfColorIdExists(int colorId);
}
