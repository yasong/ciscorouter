package ciscoroutertool.rules;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class RuleParserTest {

    @Test
    public void shouldSuccessfullyParseValidRule() throws Exception {
        //TODO: Fix test so it will use only public methods
        //Use location of known test file
        File f = new File("/home/andrew/rules/service-password-encryption-is-not-set..xml");
        Rule r = null;
        r = RuleParser.getRuleFromFile(f);
        Assert.assertNotNull("Rule returned empty object", r);
    }

}