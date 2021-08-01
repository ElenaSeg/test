import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class task3 {
	public static final String RESULT_FILENAME = "report.json";
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws IOException {
		String testsFilename = args[0];
		String valuesFilename = args[1];

		JsonNode values = mapper.readTree(new File(valuesFilename)).get("values");
		Map<Integer, String> testIdToIsPassed = new HashMap<>();
		if (values.isArray()) {
			for (JsonNode idValue : values) {
				testIdToIsPassed.put(getValueId(idValue), getValueValue(idValue));
			}
		}
		JsonNode testsJson = mapper.readTree(new File(testsFilename));
		fillTestValues(testsJson.get("tests"), testIdToIsPassed);
		try (FileWriter writer = new FileWriter(RESULT_FILENAME, false)) {
			writer.write(testsJson.toPrettyString());
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void fillTestValues(JsonNode testNodes,
									   Map<Integer, String> testIdToIsPassed) throws JsonProcessingException {
		if (testNodes.isArray()) {
			for (JsonNode testNode : testNodes) {
				if (testNode.get("value") != null) {
					Integer id = mapper.treeToValue(testNode.get("id"), Integer.class);
					((ObjectNode) testNode).put("value", testIdToIsPassed.get(id));
				}
				JsonNode values = testNode.get("values");
				if (values != null) {
					fillTestValues(values, testIdToIsPassed);
				}
			}
		}
	}

	private static Integer getValueId(JsonNode treeNode) {
		try {
			return mapper.treeToValue(treeNode.get("id"), Integer.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getValueValue(JsonNode treeNode) {
		try {
			return mapper.treeToValue(treeNode.get("value"), String.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
