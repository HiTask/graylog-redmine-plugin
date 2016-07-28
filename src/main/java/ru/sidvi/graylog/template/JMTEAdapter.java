package ru.sidvi.graylog.template;

import com.floreysoft.jmte.Engine;
import ru.sidvi.graylog.extractors.DataExtractor;

import java.util.Map;

/**
 * @author Vitaly Sidorov <mail@vitaly-sidorov.com>
 */
public class JMTEAdapter implements TemplateEngine {

    @Override
    public String processTemplate(DataExtractor extractor, String template) {
        Map<String, Object> model = extractor.extract();
        Engine engine = new Engine();
        return engine.transform(template, model);
    }
}