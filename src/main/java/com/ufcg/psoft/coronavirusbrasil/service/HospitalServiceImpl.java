package com.ufcg.psoft.coronavirusbrasil.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.enums.TipoLeito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.coronavirusbrasil.DTO.HospitalDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.PacienteDTO;
import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Paciente;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;
import com.ufcg.psoft.coronavirusbrasil.repository.HospitalRepository;
import com.ufcg.psoft.coronavirusbrasil.repository.PacienteRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Optional<Hospital> getHospitalById(Long id) {
        return hospitalRepository.findById(id);
    }

    @Override
    public Long getIdHospitalByUsuario(Usuario usuario) {
        return hospitalRepository.findByUsuario(usuario).get().getId();
    }

    @Override
    public Optional<Paciente> getPacienteByCPF(Long cpf) {
        return pacienteRepository.getPacienteByCpf(cpf);
    }

    @Override
    public Optional<Hospital> getHospitalByCNPJ(Long cnpj) {
        return hospitalRepository.findByCnpj(cnpj);
    }

    @Override
    public void removerHospitalCadastrado(Hospital hospital) {
        hospitalRepository.delete(hospital);

    }

    @Override
    public void salvarHospitalCadastrado(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> listarHospitais() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital criaHospital(HospitalDTO hospitalDTO) {
        return new Hospital(hospitalDTO);
    }

    @Override
    public Hospital atualizaHospital(HospitalDTO hospitalDTO, Hospital hospital) {
        hospital.setNome(hospitalDTO.getNome());
        hospital.setCNPJ(hospitalDTO.getCNPJ());
        hospital.setCep(hospitalDTO.getCep());
        hospital.setEndereco(hospitalDTO.getEndereço());
        hospital.setQtdLeitosEnfermaria(hospitalDTO.getQtdLeitosEnfermaria());
        hospital.setQtdLeitosUTI(hospitalDTO.getQtdLeitosUTI());
        hospital.setCasosRecuperados(hospitalDTO.getCasosRecuperados());
        hospital.setOcupacaoLeitos(hospitalDTO.getOcupacaoLeitos());

        return hospital;
    }

    @Override
    public Paciente criaPaciente(PacienteDTO pacienteDTO) {
        return new Paciente(pacienteDTO);
    }
    
	@Override
	public void atualizaLeitos(Hospital hospital, int qtdLeitos, TipoLeito tipo) {
		if(tipo.equals(TipoLeito.UTI)) {
			hospital.atualizaLeitosUTI(qtdLeitos);
		}else {
			hospital.atualizaLeitosEnfermaria(qtdLeitos);
		}
		
	}
    
    @Override
    public void adicionarPaciente(Hospital hospital, Paciente paciente, TipoLeito tipo) {
        if (tipo.equals(TipoLeito.UTI)) {
            hospital.addPacienteLeitosUTI(paciente);
        } else if (tipo.equals(TipoLeito.ENFERMARIA)) {
            hospital.addPacinteLeitosEnfermaria(paciente);
        }
        pacienteRepository.save(paciente);
    }

    @Override
    public void adicionarPacienteObito(Hospital hospital, Paciente paciente) {
        hospital.retirarPaciente(paciente);
        hospital.addPacienteObitos(paciente);
    }

    @Override
    public void adicionarPacienteRecuperado(Hospital hospital, Paciente paciente) {
        hospital.retirarPaciente(paciente);
        hospital.addPacienteRecuperados(paciente);
    }

    public int relatorioCasosRecuperados() {
        int casosRecuperados = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            casosRecuperados += h.getCasosRecuperados();
        }
        return casosRecuperados;
    }

    public int relatorioCasosRecuperadosArea(AreaDeBusca areaDeBusca, String local) {
        int casosRecuperados = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            if(areaDeBusca.equals(AreaDeBusca.CIDADE)) {
                if(h.getEndereco().getCidade().equals(local)) {
                    casosRecuperados += h.getCasosRecuperados();
                }
            } else if (areaDeBusca.equals(AreaDeBusca.ESTADO)) {
                if(h.getEndereco().getEstado().equals(local)) {
                    casosRecuperados += h.getCasosRecuperados();
                }
            } else {
                if (h.getEndereco().getRegiao().equals(local)) {
                    casosRecuperados += h.getCasosRecuperados();
                }
            }
        }
        return casosRecuperados;
    }

    public int relatorioCasosCovid() {
        int casosCovid = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            casosCovid += h.getTotalCasosConfirmados();
        }
        return casosCovid;
    }

    public int relatorioCasosCovidArea(AreaDeBusca areaDeBusca, String local) {
        int casos = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            if(areaDeBusca.equals(AreaDeBusca.CIDADE)) {
                if(h.getEndereco().getCidade().equals(local)) {
                    casos += h.getTotalCasosConfirmados();
                }
            } else if (areaDeBusca.equals(AreaDeBusca.ESTADO)) {
                if(h.getEndereco().getEstado().equals(local)) {
                    casos += h.getTotalCasosConfirmados();
                }
            } else if(areaDeBusca.equals(AreaDeBusca.REGIAO)){
                if (h.getEndereco().getRegiao().equals(local)) {
                    casos += h.getTotalCasosConfirmados();
                }
            } else {
                return relatorioCasosCovid();
            }
        }
        return casos;
    }
    private int calcula24h(int casos, List<Paciente> list){
        for (Paciente p : list) {
            Instant agora = Instant.now();
            boolean ultimas24h =
                    (!p.getInternacao().toInstant().isBefore(agora.minus(24, ChronoUnit.HOURS)))
                            && (p.getInternacao().toInstant().isBefore(agora));
            if (ultimas24h) casos += 1;
        }

        return casos;
    }

    public int relatorioCasosCovid24H() {
        int casosConfirmados24h = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            List<Paciente> list = h.getPacientesCovidPositivo();
            casosConfirmados24h = calcula24h(casosConfirmados24h, list);
            }
        return casosConfirmados24h;
    }

    public int relatorioCasosCovid24H(AreaDeBusca area, String local) {
        int casosConfirmados24h = 0;

        for (Hospital h : this.hospitalRepository.findAll()) {
            if(area.equals(AreaDeBusca.REGIAO)){
                if(h.getEndereco().getRegiao().equals(local)){
                    List<Paciente> list = h.getPacientesCovidPositivo();
                    casosConfirmados24h = calcula24h(casosConfirmados24h, list);
                }
            } else if (area.equals(AreaDeBusca.ESTADO)){
                if(h.getEndereco().getEstado().equals(local)){
                    List<Paciente> list = h.getPacientesCovidPositivo();
                    casosConfirmados24h = calcula24h(casosConfirmados24h, list);
                }
            } else if ( area.equals(AreaDeBusca.CIDADE)){
                if(h.getEndereco().getCidade().equals(local)){
                    List<Paciente> list = h.getPacientesCovidPositivo();
                    casosConfirmados24h = calcula24h(casosConfirmados24h, list);
                }
            }
        }
        return casosConfirmados24h;
    }

    public int calculaObitos() {
        int numeroDeObitos = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            numeroDeObitos += h.getObitos().size();
        }
        return numeroDeObitos;
    }

    public int calculaObitosArea(AreaDeBusca areaDeBusca, String local) {
        int obitos = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            if(areaDeBusca.equals(AreaDeBusca.CIDADE)) {
                if(h.getEndereco().getCidade().equals(local)) {
                    obitos += h.getObitos().size();
                }
            } else if (areaDeBusca.equals(AreaDeBusca.ESTADO)) {
                if(h.getEndereco().getEstado().equals(local)) {
                    obitos += h.getObitos().size();
                }
            } else if(areaDeBusca.equals(AreaDeBusca.REGIAO)){
                if (h.getEndereco().getRegiao().equals(local)) {
                    obitos += h.getObitos().size();
                }
            }
        }
        return obitos;
    }

	@Override
	public boolean verificaLeitoDisponivel(Hospital hospital, TipoLeito tipo) {
		boolean disponivel = false;
		
		if(tipo.equals(TipoLeito.UTI)) {
			disponivel = hospital.getNumeroLeitosUTIOcupados() < hospital.getQtdLeitosUTI();
		}else {
			disponivel = hospital.getNumerosLeitosEnfermariaOcupados() < hospital.getQtdLeitosEnfermaria();
		}
		
		return disponivel;
	}

	@Override
	public boolean ehPossivelAdicionarLeito(Hospital hospital, int qntLeito, TipoLeito tipo) {
		boolean ehPossivel = false;
		if(tipo.equals(TipoLeito.UTI)) {
			ehPossivel = (hospital.getQtdLeitosUTI() + qntLeito) >= 0;
		}else {
			ehPossivel = (hospital.getQtdLeitosEnfermaria() + qntLeito) >= 0;
		}
		return ehPossivel;
	}

}