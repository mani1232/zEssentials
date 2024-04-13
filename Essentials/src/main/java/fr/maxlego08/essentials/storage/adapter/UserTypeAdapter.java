package fr.maxlego08.essentials.storage.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.maxlego08.essentials.api.EssentialsPlugin;
import fr.maxlego08.essentials.api.database.dto.CooldownDTO;
import fr.maxlego08.essentials.api.database.dto.OptionDTO;
import fr.maxlego08.essentials.api.user.Option;
import fr.maxlego08.essentials.api.user.User;
import fr.maxlego08.essentials.user.ZUser;
import fr.maxlego08.essentials.zutils.utils.LocationUtils;
import org.bukkit.Location;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserTypeAdapter extends TypeAdapter<User> {

    private final EssentialsPlugin plugin;
    private final LocationUtils locationUtils = new LocationUtils() {
    };

    public UserTypeAdapter(EssentialsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void write(JsonWriter out, User value) throws IOException {
        out.beginObject();
        out.name("uniqueId").value(value.getUniqueId().toString());
        out.name("name").value(value.getName());
        if (value.getLastLocation() != null) {
            out.name("lastLocation").value(locationUtils.locationAsString(value.getLastLocation()));
        }

        out.name("options").beginObject();
        for (Map.Entry<Option, Boolean> entry : value.getOptions().entrySet()) {
            if (entry.getValue()) {
                out.name(entry.getKey().name()).value(entry.getValue());
            }
        }
        out.endObject();

        out.name("cooldowns").beginObject();
        for (Map.Entry<String, Long> entry : value.getCooldowns().entrySet()) {
            out.name(entry.getKey()).value(entry.getValue());
        }
        out.endObject();

        out.name("balances").beginObject();
        for (Map.Entry<String, BigDecimal> entry : value.getBalances().entrySet()) {
            out.name(entry.getKey()).value(entry.getValue());
        }
        out.endObject();


        out.endObject();
    }

    @Override
    public User read(JsonReader in) throws IOException {
        String name = null;
        UUID uniqueId = null; // Temporary storage for the UUID
        Map<Option, Boolean> options = new HashMap<>();
        Map<String, Long> cooldowns = new HashMap<>();
        Map<String, BigDecimal> balances = new HashMap<>();
        Location lastLocation = null;

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "uniqueId" -> uniqueId = UUID.fromString(in.nextString());
                case "name" -> name = in.nextString();
                case "lastLocation" -> lastLocation = locationUtils.stringAsLocation(in.nextString());
                case "options" -> {
                    in.beginObject();
                    while (in.hasNext()) {
                        options.put(Option.valueOf(in.nextName()), in.nextBoolean());
                    }
                    in.endObject();
                }
                case "cooldowns" -> {
                    in.beginObject();
                    while (in.hasNext()) {
                        cooldowns.put(in.nextName(), in.nextLong());
                    }
                    in.endObject();
                }
                case "balances" -> {
                    in.beginObject();
                    while (in.hasNext()) {
                        String key = in.nextName();
                        BigDecimal value = new BigDecimal(in.nextString());
                        balances.put(key, value);
                    }
                    in.endObject();
                }
            }
        }
        in.endObject();

        // Ensure that uniqueId is not null before creating a ZUser
        if (uniqueId == null) {
            throw new IOException("UniqueId is missing from the JSON input.");
        }
        User user = new ZUser(this.plugin, uniqueId); // Create the ZUser here

        // Now, set the other properties
        user.setName(name);
        user.setOptions(options.entrySet().stream().map(e -> new OptionDTO(e.getKey(), e.getValue())).collect(Collectors.toList()));
        user.setCooldowns(cooldowns.entrySet().stream().map(e -> new CooldownDTO(e.getKey(), e.getValue())).collect(Collectors.toList()));
        user.setLastLocation(lastLocation);
        balances.forEach(user::setBalance);

        return user;
    }

}
