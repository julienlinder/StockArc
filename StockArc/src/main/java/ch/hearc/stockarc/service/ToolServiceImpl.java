package ch.hearc.stockarc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.stockarc.model.Tool;
import ch.hearc.stockarc.repository.ToolRepository;

@Service
public class ToolServiceImpl implements ToolService {
	
	@Autowired
	private ToolRepository toolRepository;

	@Override
	public void save(Tool tool) {
		toolRepository.save(tool);
	}

	@Override
	public List<Tool> findAll() {
		return toolRepository.findAll();
	}

}
