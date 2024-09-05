import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Faturamento {
	public static void main(String[] args) {
		List<Double> faturamentos = lerFaturamento("faturamento.json");

		if (faturamentos.isEmpty()) {
			System.out.println("Não há dados de faturamento disponíveis.");
			return;
		}

		double menorFaturamento = faturamentos.stream().min(Double::compare).orElse(0.0);
		double maiorFaturamento = faturamentos.stream().max(Double::compare).orElse(0.0);
		double mediaFaturamento = faturamentos.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
		long diasAcimaDaMedia = faturamentos.stream().filter(valor -> valor > mediaFaturamento).count();

		System.out.println("Menor faturamento: " + menorFaturamento);
		System.out.println("Maior faturamento: " + maiorFaturamento);
		System.out.println("Número de dias com faturamento acima da média: " + diasAcimaDaMedia);
	}

	private static List<Double> lerFaturamento(String nomeArquivo) {
		List<Double> faturamentos = new ArrayList<>();
		try (InputStream inputStream = Faturamento.class.getClassLoader().getResourceAsStream(nomeArquivo);
			 Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {

			if (inputStream == null) {
				System.err.println("Arquivo não encontrado: " + nomeArquivo);
				return faturamentos;
			}

			if (!scanner.hasNext()) {
				System.err.println("Arquivo está vazio ou contém apenas espaços em branco.");
				return faturamentos;
			}

			String conteudo = scanner.useDelimiter("\\A").next();
			JSONObject json = new JSONObject(conteudo);
			JSONArray faturamentoArray = json.getJSONArray("faturamento");

			for (int i = 0; i < faturamentoArray.length(); i++) {
				JSONObject item = faturamentoArray.getJSONObject(i);
				double valor = item.getDouble("valor");
				if (valor > 0) {
					faturamentos.add(valor);
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Erro inesperado: " + e.getMessage());
			e.printStackTrace();
		}
		return faturamentos;
	}
}
