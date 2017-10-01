package com.cronutils.utils.descriptor;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * @author minidmnv
 */
public class Issue227Test {
    /**
     * Issue #227 - Getting a leaking "%s" in description output
     */
    private CronParser parser;
    private ZonedDateTime currentDateTime;

    @Before
    public void setUp() throws Exception {
        currentDateTime = ZonedDateTime.of(LocalDateTime.of(2016, 12, 20, 12, 00),
                ZoneId.systemDefault());

        parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
    }

    @Test
    public void testProperDescriptorOutput() {
        Cron cron = parser.parse("0 5-35/30 * * * ?");
        CronDescriptor descriptor = CronDescriptor.instance(Locale.US);

        assertEquals("every 30 minutes between 5 and 35", descriptor.describe(cron));
    }

}
