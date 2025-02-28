package com.example.mealplanner.fragments.explore.view;

import java.util.HashMap;
import java.util.Map;

public abstract class FlagMapper {

    private static final Map<String, String> FLAGS = new HashMap<>();

    static {
        FLAGS.put("American", "https://cdn-icons-png.flaticon.com/512/197/197484.png");
        FLAGS.put("British", "https://cdn-icons-png.flaticon.com/512/10576/10576577.png");
        FLAGS.put("Canadian", "https://cdn-icons-png.flaticon.com/512/12360/12360231.png");
        FLAGS.put("Chinese", "https://cdn-icons-png.flaticon.com/512/9906/9906454.png");
        FLAGS.put("Croatian", "https://cdn-icons-png.flaticon.com/512/12360/12360474.png");
        FLAGS.put("Dutch", "https://cdn-icons-png.flaticon.com/512/10600/10600976.png");
        FLAGS.put("Egyptian", "https://cdn-icons-png.flaticon.com/512/12360/12360503.png");
        FLAGS.put("Filipino", "https://cdn-icons-png.flaticon.com/512/16022/16022850.png");
        FLAGS.put("French", "https://cdn-icons-png.flaticon.com/512/197/197560.png");
        FLAGS.put("Greek", "https://cdn-icons-png.flaticon.com/512/16022/16022148.png");
        FLAGS.put("Indian", "https://cdn-icons-png.flaticon.com/512/16022/16022214.png");
        FLAGS.put("Irish", "https://cdn-icons-png.flaticon.com/512/10576/10576451.png");
        FLAGS.put("Italian", "https://cdn-icons-png.flaticon.com/512/10576/10576616.png");
        FLAGS.put("Jamaican", "https://cdn.countryflags.com/thumbs/jamaica/flag-round-500.png");
        FLAGS.put("Japanese", "https://cdn-icons-png.flaticon.com/512/4628/4628642.png");
        FLAGS.put("Kenyan", "https://cdn-icons-png.flaticon.com/512/12361/12361080.png");
        FLAGS.put("Malaysian", "https://cdn-icons-png.flaticon.com/512/10597/10597843.png");
        FLAGS.put("Mexican", "https://cdn-icons-png.flaticon.com/512/10602/10602501.png");
        FLAGS.put("Moroccan", "https://cdn-icons-png.flaticon.com/512/4489/4489252.png");
        FLAGS.put("Norwegian", "https://cdn-icons-png.flaticon.com/512/4628/4628652.png");
        FLAGS.put("Polish", "https://cdn-icons-png.flaticon.com/512/4628/4628690.png");
        FLAGS.put("Portuguese", "https://cdn.countryflags.com/thumbs/portugal/flag-round-500.png");
        FLAGS.put("Russian", "https://cdn-icons-png.flaticon.com/512/4628/4628645.png");
        FLAGS.put("Spanish", "https://cdn-icons-png.flaticon.com/512/10601/10601048.png");
        FLAGS.put("Thai", "https://cdn-icons-png.flaticon.com/512/10576/10576553.png");
        FLAGS.put("Tunisian", "https://cdn-icons-png.flaticon.com/512/10562/10562141.png");
        FLAGS.put("Turkish", "https://cdn-icons-png.flaticon.com/512/4628/4628673.png");
        FLAGS.put("Ukrainian", "https://cdn-icons-png.flaticon.com/512/4628/4628718.png");
        FLAGS.put("Uruguayan", "https://cdn-icons-png.flaticon.com/512/12360/12360299.png");
        FLAGS.put("Vietnamese", "https://cdn-icons-png.flaticon.com/512/4855/4855906.png");
    }

    public static String getFlag(String country) {
        return FLAGS.get(country);
    }
}
