package ch.hearc.stockarc.service;

import java.util.List;

import ch.hearc.stockarc.model.Tool;

public interface ToolService {
	void save(Tool tool);

	List<Tool> findAll();
}
