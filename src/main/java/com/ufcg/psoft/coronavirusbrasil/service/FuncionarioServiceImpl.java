package com.ufcg.psoft.coronavirusbrasil.service;

import java.util.*;

import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.model.*;
import com.ufcg.psoft.coronavirusbrasil.util.Constants;
import com.ufcg.psoft.coronavirusbrasil.util.calculator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.coronavirusbrasil.DTO.FuncionarioDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.SintomasDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;
import com.ufcg.psoft.coronavirusbrasil.repository.FuncionarioRepository;
import com.ufcg.psoft.coronavirusbrasil.repository.PacienteRepository;;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public Optional<Funcionario> getFuncionarioById(Long Id) {
        return funcionarioRepository.findById(Id);
    }

    @Override
    public Optional<Funcionario> getFuncionarioByCPF(Long cpf) {
        return funcionarioRepository.findByCPF(cpf);
    }

	@Override
	public List<Paciente> getPacientesSintomas(Sintomas sintomas, String nivelRelatorio, String regiao) {
		
		List<Paciente> paciente = new ArrayList<Paciente>();
		String regiaoUpperCase = regiao.toUpperCase();
		
		pacienteRepository.findAll().forEach((p)-> {
			
			if (p.getSintomas().equals(sintomas)) {
				
				if(nivelRelatorio.toUpperCase().equals("CIDADE") && p.getEndereco().getCidade().toUpperCase().equals(regiaoUpperCase)) {
					paciente.add(p);
				} else if(nivelRelatorio.toUpperCase().equals("ESTADO") && p.getEndereco().getEstado().toUpperCase().equals(regiaoUpperCase)) {
					paciente.add(p);
				}
				
			}
		});
		
		return paciente;
	}
    
	@Override
	public List<Paciente> getPacientesObitoSintomas(Sintomas sintomas, String nivelRelatorio, String regiao) {
		
		List<Paciente> obitos = new ArrayList<Paciente>();
		List<Paciente> pacientes = pacienteRepository.findAll();
		String regiaoUpperCase = regiao.toUpperCase();
		
		for(Paciente p: pacientes) {
			
			if (p.getSintomas().equals(sintomas) && p.foiAObito()) {
				if(nivelRelatorio.toUpperCase().equals("CIDADE") && p.getEndereco().getCidade().toUpperCase().equals(regiaoUpperCase)) {
					obitos.add(p);
				} else if(nivelRelatorio.toUpperCase().equals("ESTADO") && p.getEndereco().getEstado().toUpperCase().equals(regiaoUpperCase)) {
					obitos.add(p);
				}
			}
		}
		
		return obitos;
	}
	
    @Override
    public Sintomas criaSintomas(SintomasDTO sintomasDTO) {
        return new Sintomas(sintomasDTO);
    }

    @Override
    public List<Paciente> getPacientesSemana(Date dataInicio, String nivelRelatorio, String regiao) {

        List<Paciente> paciente = new ArrayList<Paciente>();

        pacienteRepository.findAll().forEach((p) -> {

            @SuppressWarnings("deprecation")
            int diferenca = dataInicio.getDay() - p.getInicioSintomas().getDay();

            if (diferenca <= 7 && diferenca > 0) {

                if (nivelRelatorio.equals("cidade") && p.getEndereco().getCidade().equals(regiao)) {
                    paciente.add(p);
                } else if (nivelRelatorio.equals("estado") && p.getEndereco().getEstado().equals(regiao)) {
                    paciente.add(p);
                }
            }

        });

        return paciente;
    }


    @Override
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario criaFuncionario(FuncionarioDTO funcionarioDTO) {
        Sexo sexo = (funcionarioDTO.getEhHomem()) ? Sexo.MASCULINO : Sexo.FEMININO;

        Funcionario funcionario = new Funcionario(funcionarioDTO.getNome(), sexo, funcionarioDTO.getCPF());

        funcionarioRepository.save(funcionario);

        return funcionario;
    }

    @Override
    public void removeFuncionario(Funcionario funcionario) {
        funcionarioRepository.delete(funcionario);
    }

    @Override
    public void salvaFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario atualizaFuncionario(FuncionarioDTO funcionarioDTO, Funcionario funcionario) {
        Sexo sexo = (funcionarioDTO.getEhHomem()) ? Sexo.MASCULINO : Sexo.FEMININO;

        funcionario.setCpf(funcionarioDTO.getCPF());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setSexo(sexo);

        funcionarioRepository.save(funcionario);

        return funcionario;
    }

    public String relatorioMortesCovid(int faixaEtariaInicio, int faixaEtariaFim, Sexo sexo, AreaDeBusca areaDeBusca, String nomeArea) {
        Calculador calculador;
        String relatorio = "";
        if (areaDeBusca.equals(AreaDeBusca.BRASIL)) {
            calculador = new CalculadorObitosBrasil();
            relatorio = calculador.calculaObitos(sexo, faixaEtariaInicio, faixaEtariaFim, Constants.BRASIL);
        } else if (areaDeBusca.equals(AreaDeBusca.ESTADO)) {
            calculador = new CalculadorObitosEstado();
            relatorio = calculador.calculaObitos(sexo, faixaEtariaInicio, faixaEtariaFim, nomeArea);
        } else {
            calculador = new CalculaObitosCidade();
            relatorio = calculador.calculaObitos(sexo, faixaEtariaInicio, faixaEtariaFim, nomeArea);
        }
        return relatorio;
    }

    public String relatorioHospitalizacoesCovid(int faixaEtariaInicio, int faixaEtariaFim, Sexo sexo, AreaDeBusca areaDeBusca, String nomeArea) {

        return CalculadorHospitalizacoes.calculadorHospitalizacoes(sexo,faixaEtariaInicio,faixaEtariaFim,areaDeBusca,nomeArea);
    }

}
