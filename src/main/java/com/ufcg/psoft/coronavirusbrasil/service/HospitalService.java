package com.ufcg.psoft.coronavirusbrasil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.coronavirusbrasil.DTO.HospitalDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.PacienteDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.enums.TipoLeito;
import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Paciente;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;

public interface HospitalService {

    public Optional<Hospital> getHospitalById(Long id);

    public Optional<Hospital> getHospitalByCNPJ(Long cnpj);

    public void removerHospitalCadastrado(Hospital hospital);

    public void salvarHospitalCadastrado(Hospital hospital);

    public List<Hospital> listarHospitais();

    public Hospital criaHospital(HospitalDTO hospitalDTO);

    public Hospital atualizaHospital(HospitalDTO hospitalDTO, Hospital hospital);

    public Long getIdHospitalByUsuario(Usuario usuario);

    public void atualizaLeitos(Hospital hospital, int qtdLeitos, TipoLeito tipo);
    
    public void adicionarPaciente(Hospital hospital, Paciente paciente, TipoLeito tipo);

    public Paciente criaPaciente(PacienteDTO paciente);

    public Optional<Paciente> getPacienteByCPF(Long cpf);

    public void adicionarPacienteObito(Hospital hospital, Paciente paciente);

    public void adicionarPacienteRecuperado(Hospital hospital, Paciente paciente);

    public int relatorioCasosRecuperados();

    public int calculaObitos();

    public int relatorioCasosCovid();

    public int relatorioCasosCovid24H();

    public int relatorioCasosRecuperadosArea(AreaDeBusca areaDeBusca, String local);

    public int relatorioCasosCovidArea(AreaDeBusca areaDeBusca, String regiao);

    public int calculaObitosArea(AreaDeBusca areaDeBusca, String regiao);

    public int relatorioCasosCovid24H(AreaDeBusca areaDeBusca, String regiao);
    
    public boolean verificaLeitoDisponivel(Hospital hospital, TipoLeito tipo);
    
    public boolean ehPossivelAdicionarLeito(Hospital hospital, int qntLeito, TipoLeito tipo);
}