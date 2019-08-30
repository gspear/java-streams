package mockdata;

import beans.Car;
import beans.Device;
import beans.Person;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class MockData {

    public static ImmutableList<Person> getPeople() throws IOException {
        InputStream inputStream = Resources.getResource("people.json").openStream();
        String json = IOUtils.toString(inputStream);
        Type listType = new TypeToken<ArrayList<Person>>() {
        }.getType();
        List<Person> people = new Gson().fromJson(json, listType);
        return ImmutableList.copyOf(people);
    }

    public static ImmutableList<Car> getCars() throws IOException {
        InputStream inputStream = Resources.getResource("cars.json").openStream();
        String json = IOUtils.toString(inputStream);
        Type listType = new TypeToken<ArrayList<Car>>() {
        }.getType();
        List<Car> cars = new Gson().fromJson(json, listType);
        return ImmutableList.copyOf(cars);
    }

    public static ImmutableList<Person> getPeople(String name) throws IOException {
        InputStream inputStream = Resources.getResource(name).openStream();
        String json = IOUtils.toString(inputStream);
        Type listType = new TypeToken<ArrayList<Person>>() {
        }.getType();
        List<Person> people = new Gson().fromJson(json, listType);
        return ImmutableList.copyOf(people);
    }

    public static ImmutableList<Device> getDevices() throws IOException {
        InputStream inputStream = Resources.getResource("devices.json").openStream();
        String json = IOUtils.toString(inputStream);
        Type listType = new TypeToken<ArrayList<Device>>() {
        }.getType();
        List<Device> devices = new Gson().fromJson(json, listType);
        return ImmutableList.copyOf(devices);
    }

}
