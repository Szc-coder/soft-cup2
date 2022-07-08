import os
import uuid
import zipfile
import cv2
import numpy as np
from paddlers.deploy.predictor import Predictor


def readZip(task):
    images = []
    # 变化检测任务特殊点，返回一个list[(image_a, image_b)]
    if task.taskId == 2:
        images_A_file = zipfile.ZipFile(task.fileLocation[0], mode='r')
        images_B_file = zipfile.ZipFile(task.fileLocation[1], mode='r')
        for name in images_A_file.namelist():
            if '.jpg' not in name:
                continue
            a_file = images_A_file.open(name, mode='r')
            b_file = images_B_file.open(name, mode='r')
            content_a = a_file.read()
            content_b = b_file.read()
            image_a = np.asarray(bytearray(content_a), dtype='uint8')
            image_b = np.asarray(bytearray(content_b), dtype='uint8')
            image_a = cv2.imdecode(image_a, cv2.IMREAD_COLOR)
            image_b = cv2.imdecode(image_b, cv2.IMREAD_COLOR)
            images.append((image_a, image_b))
            a_file.close()
            b_file.close()
        images_A_file.close()
        images_B_file.close()
    else:
        with zipfile.ZipFile(task.fileLocation[0], mode='r') as zfile:  # 只读方式打开压缩包
            for name in zfile.namelist():  # 获取zip文档内所有文件的名称列表
                if '.jpg' not in name and '.png' not in name:  # 仅读取.jpg图片，过滤掉文件夹，及其他非.jpg后缀文件
                    continue
                with zfile.open(name, mode='r') as image_file:
                    content = image_file.read()  # 一次性读入整张图片信息
                    image = np.asarray(bytearray(content), dtype='uint8')
                    image = cv2.imdecode(image, cv2.IMREAD_COLOR)
                    images.append(image)

    return images


def readFiles(task):
    images = []
    for file_name in task.fileLocation:
        img = cv2.imread(file_name)
        images.append(img)
    return images


def readImages(task):
    images = None
    if task.mode == 0:
        images = readZip(task)

    if task.mode == 1:
        images = readFiles(task)

    return images


class Predictors:
    def __init__(self):
        # Predictor.predict: img_file(List[str or tuple or np.ndarray], str, tuple, or np.ndarray):
        self.ChangeDetPredictor = Predictor(
            model_dir='models/ChangeDet/',
            use_gpu=False,
            gpu_id=0,
            cpu_thread_num=1,
            use_mkl=True,
            mkl_thread_num=4,
            use_trt=True,
            use_glog=False,
            memory_optimize=True,
            max_trt_batch_size=1,
            trt_precision_mode='float32'
        )
        self.TargetExtractionPredictor = Predictor(
            model_dir='models/mbtq/',
            use_gpu=False,
            gpu_id=0,
            cpu_thread_num=1,
            use_mkl=True,
            mkl_thread_num=4,
            use_trt=True,
            use_glog=False,
            memory_optimize=True,
            max_trt_batch_size=1,
            trt_precision_mode='float32'
        )
        self.ClassificationOfFeaturesPredictor = Predictor(
            model_dir='models/diwu_class/',
            use_gpu=False,
            gpu_id=0,
            cpu_thread_num=1,
            use_mkl=True,
            mkl_thread_num=4,
            use_trt=True,
            use_glog=False,
            memory_optimize=True,
            max_trt_batch_size=1,
            trt_precision_mode='float32'
        )
        self.ObjectDetectionPredictor = Predictor(
            model_dir='models/mbjc/',
            use_gpu=False,
            gpu_id=0,
            cpu_thread_num=1,
            use_mkl=True,
            mkl_thread_num=4,
            use_trt=True,
            use_glog=False,
            memory_optimize=True,
            max_trt_batch_size=1,
            trt_precision_mode='float32'
        )

    def __call__(self, task, images):
        # 0目标检测、1目标提取、2变化检测、3地物分类
        results = []
        if task.taskId == 0:
            for i in images:
                result = self.ObjectDetectionPredictor.predict(img_file=i)
                results.append(result)

        if task.taskId == 1:
            for i in images:
                result = self.TargetExtractionPredictor.predict(img_file=i)
                results.append(result['label_map'])

        if task.taskId == 3:
            for i in images:
                result = self.ClassificationOfFeaturesPredictor.predict(img_file=i)
                results.append(result['label_map'])

        if task.taskId == 2:
            # 变化检测的result是nparray
            result = self.ChangeDetPredictor.predict(img_file=tuple(images))
            results.append(result[0])

        return results


savePath = '  D:\\dev\\soft-cup2\\back-end\\back-end-service\\service\\src\\main\\resources\\sources_images\\'


def saveResultByTask(result, images, task):
    if task.taskId != 0:
        for i, data in enumerate(result):
            img = visionResultMask(data)
            cv2.imwrite(savePath+task.uuid+'\\'+task.fileLocation[i], img)


def visionResultMask(mask, task):
    if task.taskId == 1:
        pass

