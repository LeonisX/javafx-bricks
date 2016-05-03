package sample;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {

        Image image = new Image(getClass().getResourceAsStream(""));
        final ImageView iv1 = new ImageView();
        iv1.setImage(image);

        primaryStage.setTitle("Image Editor Using JavaFX");

        final Reflection r = new Reflection();
        r.setFraction(1);

        final BoxBlur bb = new BoxBlur(5, 5, 3);

        Distant light = new Distant();
        light.setAzimuth(-135.0f);
        final Lighting l = new Lighting(light);
        l.setSurfaceScale(5.0f);

        final DropShadow ds = new DropShadow(10, 30.0, 3.0, Color.BLACK);

        final InnerShadow is = new InnerShadow(10, 20.0, 2.0, Color.RED);

        Button btnChooseImage = new Button("Open Image File...");
        btnChooseImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            // FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
            // fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);
            Image ima = new Image(file.toURI().toString());
            iv1.setImage(ima);
        });

        Button btnLightEffect = new Button("Light Effect");
        btnLightEffect.setOnAction(event -> iv1.setEffect(l));

        Button btnOuterShadow = new Button("Drop Shadow");
        btnOuterShadow.setOnAction(event -> iv1.setEffect(ds));

        Button btnInnerShadow = new Button("Inner Shadow");
        btnInnerShadow.setOnAction(event -> iv1.setEffect(is));

        Button btnBlur = new Button("Blur");
        btnBlur.setOnAction(event -> iv1.setEffect(bb));

        Button btnReflection = new Button("Reflection");
        btnReflection.setOnAction(event -> iv1.setEffect(r));

        VBox root = new VBox(5);

        HBox hbEffects = new HBox(5);
        HBox hbEffectsSlider = new HBox(5);
        HBox hbImage = new HBox();

        hbEffects.getChildren().addAll(btnChooseImage, btnLightEffect, btnOuterShadow, btnInnerShadow, btnBlur, btnReflection);
        hbImage.getChildren().add(iv1);

        final Label opacityCaption = new Label("Opacity Level:");
        final Slider opacityLevel = new Slider(0, 1, 1);
        opacityLevel.valueProperty().addListener((ov, old_val, new_val) -> {
            iv1.setOpacity(new_val.doubleValue());
        });

        final Label sepiaCaption = new Label("Sepia Tone:");
        final Slider sepiaTone = new Slider(0, 1, 1);
        final SepiaTone sepiaEffect = new SepiaTone();
        sepiaTone.valueProperty().addListener((ov, old_val, new_val) -> {
            sepiaEffect.setLevel(new_val.doubleValue());
            iv1.setEffect(sepiaEffect);
        });

        final Label scalingCaption = new Label("Zoom :");
        final Slider scaling = new Slider(0.5, 1, 1);
        scaling.valueProperty().addListener((ov, old_val, new_val) -> {
            iv1.setScaleX(new_val.doubleValue());
            iv1.setScaleY(new_val.doubleValue());
        });

        hbEffectsSlider.getChildren().addAll(opacityCaption, opacityLevel, sepiaCaption, sepiaTone, scalingCaption, scaling);

        root.getChildren().add(hbEffects);
        root.getChildren().add(hbEffectsSlider);
        root.getChildren().add(hbImage);

        primaryStage.setScene(new Scene(root, 800, 500, Color.BLANCHEDALMOND));
        primaryStage.show();
    }
}