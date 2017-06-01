import time
import io
import threading
import picamera


class Camera(object):
    thread = None
    frame = None
    last_access = 0

    def initialize(self):
        if Camera.thread is None:
            Camera.thread = threading.Thread(target=self._thread)
            Camera.thread.start()
            
            while self.frame is None:
                time.sleep(0)
    

    def get_frame(self):
        Camera.last_access = time.time()
        self.initialize()
        return self.frame

    def close(self):
        """
        Finalizes the state of the camera.
        After successfully constructing a :class:`PiCamera` object, you should
        ensure you call the :meth:`close` method once you are finished with the
        camera (e.g. in the ``finally`` section of a ``try..finally`` block).
        This method stops all recording and preview activities and releases all
        resources associated with the camera; this is necessary to prevent GPU
        memory leaks.
        """
        for port in list(self._encoders):
            self.stop_recording(splitter_port=port)
        assert not self.recording
        for overlay in list(self._overlays):
            self.remove_overlay(overlay)
        if self._preview:
            self._preview.close()
            self._preview = None
        if self._splitter:
            self._splitter.close()
            self._splitter = None
        if self._camera:
            self._camera.close()
            self._camera = None
        exc, self._camera_exception = self._camera_exception, None
        if exc:
            raise exc
    

    @classmethod
    def _thread(cls):
        with picamera.PiCamera() as camera:
            camera.resolution = (320, 240)
            camera.hflip = True
            camera.vflip = True

            camera.start_preview()
            time.sleep(2)

            stream = io.BytesIO()
            start_time = time.time()
            for foo in camera.capture_continuous(stream, 'jpeg',
                                                 use_video_port=True):
                stream.seek(0)
                cls.frame = stream.read()

                stream.seek(0)
                stream.truncate()

                    

        cls.thread = None
