import json

import cv2
import pika
import numpy as np
from InfTask import Task
from utils import readImages, Predictors, saveResultByTask

# RabbitMq
credentials = pika.PlainCredentials('root', 'qwe123580.00')
connection = pika.BlockingConnection(pika.ConnectionParameters('182.61.3.70', credentials=credentials))
channel = connection.channel()
channel.queue_declare(queue='InferenceChannel')

# Predictors
predictors = Predictors()


def callback(ch, method, properties, body):
    body_json_str = body.decode('UTF-8')
    body_json = json.loads(body_json_str)
    task = Task()
    task.__dict__ = body_json
    # 0目标检测、1目标提取、2变化检测、3地物分类
    # 文件命名：用户id+任务类型+任务时间
    # 0:zip, 1:files
    images = None
    # 读取图片
    try:
        # images = readImages(task)
        # if len(images) < 1:
        #     ch.basic_nack(delivery_tag=method.delivery_tag, requeue=False)
        # result = predictors.__call__(task, images)
        # ans = saveResultByTask(result, images, task)
        ch.basic_nack(delivery_tag=method.delivery_tag, requeue=False)

    except Exception as e:
        print(e)


channel.basic_consume(on_message_callback=callback, queue='InferenceChannel', auto_ack=False)
channel.start_consuming()
