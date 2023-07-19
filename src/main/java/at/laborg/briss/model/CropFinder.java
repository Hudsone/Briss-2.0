package at.laborg.briss.model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public final class CropFinder {

	private CropFinder() {
	}

	public static float[] getAutoCropFloats(final BufferedImage image) {

		WritableRaster raster = image.getRaster();

		// double[] sdOfDerivationX = ImageFinderUtil.createSdOfDerivation(raster, ImageFinderUtil.X_AXIS);
		// double[] sdOfDerivationY = ImageFinderUtil.createSdOfDerivation(raster, ImageFinderUtil.Y_AXIS);
		double[] sdOfDerivationX = ImageFinderUtil.minFrom2dTo1d(raster, ImageFinderUtil.X_AXIS);
		double[] sdOfDerivationY = ImageFinderUtil.minFrom2dTo1d(raster, ImageFinderUtil.Y_AXIS);

		int positionXLeft = ImageFinderUtil.findPosition(sdOfDerivationX, ImageFinderUtil.ORIENTATION_LEFT, 100);
		int positionYTop = ImageFinderUtil.findPosition(sdOfDerivationY, ImageFinderUtil.ORIENTATION_TOP, 100);
		int positionXRight = ImageFinderUtil.findPosition(sdOfDerivationX, ImageFinderUtil.ORIENTATION_RIGHT, 100);
		int positionYBottom = ImageFinderUtil.findPosition(sdOfDerivationY, ImageFinderUtil.ORIENTATION_BOTTOM, 100);

		float[] result = new float[4];
		result[0] = (positionXLeft / (float) image.getWidth());
		result[1] = ((image.getHeight() - positionYBottom) / (float) image.getHeight());
		result[2] = ((image.getWidth() - positionXRight) / (float) image.getWidth());
		result[3] = (positionYTop / (float) image.getHeight());
		return result;
	}
}
