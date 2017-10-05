package com.aniakanl.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ExBufferedInputStream extends BufferedInputStream{

	public ExBufferedInputStream(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Reads bytes from this input stream until it reaches the specified marker in the stream 
	 * @param marker the stop marker 
	 * @param includeMarker whether marker must be returned
	 * @return the bytes read from the stream, or null if the marker is not found in the stream
	 * @throws IOException
	 */
	public byte[] read(byte[] marker, boolean includeMarker) throws IOException
	{
		// TODO implement the logic for includeMarker
		byte[] result = null;

		int unseenMarkerIndex = 0;

		int markerEndLoc = 0;

		int readSize = 0;

		byte[] tmpBuffer = new byte[1024];


		boolean isMarkerFound = false;
		
		while (isMarkerFound == false && (readSize = read(tmpBuffer, 0, tmpBuffer.length)) >= 0 ) {
			
			if (readSize > 0) {
				for (int i = 0; i < readSize; i++) {
					if (unseenMarkerIndex < marker.length && marker[unseenMarkerIndex] == tmpBuffer[i]) {
						
						unseenMarkerIndex++;
					}
					else if(unseenMarkerIndex == marker.length){
						markerEndLoc = i-1;
						isMarkerFound = true;
						// move the pos back to the first index after the marker in the input stream
						pos -= (readSize - markerEndLoc) + 1;
						break;
					}
					else {
						unseenMarkerIndex = 0;
					}
				}
				
				if (isMarkerFound) {
					// remove the buffer after marker
					tmpBuffer = Arrays.copyOfRange(tmpBuffer, 0, ++markerEndLoc);
				}

				if (result == null) {
					
					result = Arrays.copyOf(tmpBuffer, tmpBuffer.length);
				}
				else
				{
					byte[] tmpRes = result;
					
					result = new byte[result.length + tmpBuffer.length];
					System.arraycopy(tmpRes, 0, result, 0, tmpRes.length);
					System.arraycopy(tmpBuffer, 0, result, tmpRes.length, tmpBuffer.length);
				}
				
			}
		}

		return result;

	}

}
