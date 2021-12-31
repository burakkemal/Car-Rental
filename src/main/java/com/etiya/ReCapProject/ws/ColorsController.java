package com.etiya.ReCapProject.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.business.dtos.ColorSearchListDto;
import com.etiya.ReCapProject.business.requests.colorRequests.CreateColorRequest;
import com.etiya.ReCapProject.business.requests.colorRequests.DeleteColorRequest;
import com.etiya.ReCapProject.business.requests.colorRequests.UpdateColorRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	
	private ColorService colorService;

	@Autowired
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	@GetMapping("getAll")
	public DataResult<List<ColorSearchListDto>> getAll(){
		return this.colorService.getAll();
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
		return this.colorService.update(updateColorRequest);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest){
		return this.colorService.add(createColorRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<ColorSearchListDto> getColor(@RequestParam("colorId") int colorId){
		return this.colorService.getByColorId(colorId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
