package net.darkhax.additionalbanners.common;

import net.minecraft.world.item.DyeColor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.StringJoiner;

public class GenerateFiles {

    private static File assets = dir(new File("output/assets/additionalbanners"));
    private static File data = dir(new File("output/data/additionalbanners"));

    public static void main(String[] args) throws Exception {
        for (PatternType type : PatternType.values()) {
            Files.writeString(new File("output/" + type.main + "_banner_pattern.json").toPath(), """
                    {
                      "type": "minecraft:crafting_shaped",
                      "category": "misc",
                      "group": "boat",
                      "key": {
                        "P": "minecraft:paper",
                        "P": "minecraft:"
                      },
                      "pattern": [
                        " A ",
                        "APA",
                        " A "
                      ],
                      "result": {
                        "id": "additionalbanners:%1$s_banner_pattern"
                      }
                    }""".formatted(type.main), StandardCharsets.UTF_8);
        }
//        generateBanners();
//        generateBannerTags();
//        generateItems();
//        generateItemModels();
    }

    public static String capitalizeWords(String input) {

        input = input.replace("_", " ");

        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)));

                if (word.length() > 1) {
                    result.append(word.substring(1).toLowerCase());
                }
            }

            result.append(" ");
        }

        // Remove the trailing space
        return result.toString().trim();
    }

    private static void generateBanners() throws IOException {
        final File bannerDir = dir(new File(data, "banner_pattern"));
        for (PatternType type : PatternType.values()) {
            if (type.hasVariants()) {
                for (String variant : type.variants) {
                    final String patternName = type.main + "_" + variant;
                    Files.writeString(new File(bannerDir, patternName + ".json").toPath(), """
                            {
                              "asset_id": "additionalbanners:%1$s",
                              "translation_key": "block.additionalbanners.banner.%1$s"
                            }""".formatted(patternName), StandardCharsets.UTF_8);
                }
            }
            else {
                Files.writeString(new File(bannerDir, type.main + ".json").toPath(), """
                            {
                              "asset_id": "additionalbanners:%1$s",
                              "translation_key": "block.additionalbanners.banner.%1$s"
                            }""".formatted(type.main), StandardCharsets.UTF_8);
            }
        }
    }

    private static void generateBannerTags() throws IOException {
        final File bannerDir = dir(new File(data, "tags/banner_pattern/pattern_item"));
        for (PatternType type : PatternType.values()) {
            StringJoiner joiner = new StringJoiner("," + System.lineSeparator());
            if (type.hasVariants()) {
                for (String variant : type.variants) {
                    joiner.add("    \"additionalbanners:" + type.main + "_" + variant + "\"");
                }
            }
            else {
                joiner.add("    \"additionalbanners:" + type.main + "\"");
            }

            Files.writeString(new File(bannerDir, type.main + ".json").toPath(), """
                    {
                      "values": [
                    %1$s
                      ]
                    }""".formatted(joiner.toString()), StandardCharsets.UTF_8);
        }
    }

    private static void generateItems() throws IOException {
        final File itemDir = dir(new File(assets, "items"));
        for (PatternType type : PatternType.values()) {
            Files.writeString(new File(itemDir, type.main + "_banner_pattern.json").toPath(), """
                    {
                      "model": {
                        "type": "minecraft:model",
                        "model": "additionalbanners:item/%1$s_banner_pattern"
                      }
                    }""".formatted(type.main), StandardCharsets.UTF_8);
        }
    }

    private static void generateItemModels() throws IOException {
        final File itemDir = dir(new File(assets, "models/item"));
        for (PatternType type : PatternType.values()) {
            Files.writeString(new File(itemDir, type.main + "_banner_pattern.json").toPath(), """
                    {
                      "parent": "minecraft:item/generated",
                      "textures": {
                        "layer0": "additionalbanners:item/%1$s_banner_pattern"
                      }
                    }""".formatted(type.main), StandardCharsets.UTF_8);
        }
    }

    private static File dir(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
