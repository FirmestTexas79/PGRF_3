package objectdata;

import org.jetbrains.annotations.NotNull;
import transform.Mat4;
import transform.Mat4Identity;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final @NotNull List<Solid> solids;
    private final @NotNull List<Mat4> modelMats;

    public Scene() {
        solids=new ArrayList<>();
        modelMats=new ArrayList<>();
    }

    public  @NotNull List<Mat4> getModelMats() {
        return modelMats;
    }

    public @NotNull List<Solid> getSolids() {
        return solids;
    }

    public void addSolid(final @NotNull Solid solid){
        solids.add(solid);
        modelMats.add(new Mat4Identity());
    }

    public void addSolid(final @NotNull Solid solid,final @NotNull Mat4 modelMat){
        solids.add(solid);
        modelMats.add(new Mat4Identity());
    }
    public void removeSolid(final @NotNull Solid solid) {
        if (isInScene(solid)) {
            int index = solids.indexOf(solid);
            solids.remove(index);
            modelMats.remove(index);
        }
    }
    public boolean isInScene(final @NotNull Solid solid) {
        return solids.contains(solid);
    }

}
