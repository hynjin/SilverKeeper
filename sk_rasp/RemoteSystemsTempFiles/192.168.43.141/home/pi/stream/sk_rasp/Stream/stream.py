'''
Created on 2017. 5. 29.

@author: mac
'''

if __name__ == '__main__':
    from picamera import PiCamera
    from time import sleep

    camera = PiCamera()

    camera.start_preview()
    sleep(10)
    camera.stop_preview()
    pass
