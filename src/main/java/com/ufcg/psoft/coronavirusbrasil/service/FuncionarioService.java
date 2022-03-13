package com.ufcg.psoft.coronavirusbrasil.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.coronavirusbrasil.DTO.FuncionarioDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.SintomasDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;
import com.ufcg.psoft.coronavirusbrasil.model.Funcionario;
import com.ufcg.psoft.coronavirusbrasil.model.Paciente;
import com.ufcg.psoft.coronavirusbrasil.model.Sintomas;

public interface FuncionarioService {

	public Optional<Funcionario> getFuncionarioById(Long Id);
	
	public Optional<Funcionario> getFuncionarioByCPF(Long cpf);
	
	public List<Funcionario> listarFuncionarios();
	
	public Funcionario criaFuncionario(FuncionarioDTO funcionarioDTO);
	
	public void removeFuncionario(Funcionario funcionario);
	
	public void salvaFuncionario(Funcionario funcionario);
	
	public Funcionario atualizaFuncionario(FuncionarioDTO funcionarioDTO, Funcionario funcionario);
	
	public List<Paciente> getPacientesSemana(Date dataInicio, String nivelRelatorio, String regiao);

	public List<Paciente> getPacientesSintomas(Sintomas sintomas, String nivelRelatorio, String regiao);
	
	public List<Paciente> getPacientesObitoSintomas(Sintomas sintomas, String nivelRelatorio, String regiao);

	public Sintomas criaSintomas(SintomasDTO sintomasDTO);

	public String relatorioMortesCovid(int faixaEtariaInicio, int faixaEtariaFim, Sexo sexo, AreaDeBusca areaDeBusca, String nomeArea);

    public String relatorioHospitalizacoesCovid(int faixaEtariaInicio, int faixaEtariaFim, Sexo sexo, AreaDeBusca areaDeBusca, String nomeArea);
}
