package pl.rekeep.app.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

@Slf4j
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {

        if (jsonNode == null) {
            log.warn("Node is null, return null");
            return null;
        }

        return jsonNode.toString();

    }

    @Override
    public JsonNode convertToEntityAttribute(String jsonNodeString) {
        if ( StringUtils.isEmpty(jsonNodeString) )
        {
            log.warn( "jsonNodeString input is empty, returning null" );
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.readTree( jsonNodeString );
        }
        catch( JsonProcessingException e )
        {
            log.error( "Error parsing jsonNodeString", e );
        }
        return null;
    }

}
