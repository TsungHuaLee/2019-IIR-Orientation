# -*- coding: UTF-8 -*-
from django.shortcuts import render
from django.http import HttpResponse
from datetime import datetime
from myapp.models import User, Userinfo, KkboxSong, UserlikeRecord
from django.http import JsonResponse
import json

# Create your views here.
def index(request):
    name = "landscape"
    current_time = str(datetime.now())
    return render(request, 'myapp/index.html', locals())

def tag_page(request):
    animals = ['cat', 'dog', 'pikachu']
    return render(request, 'myapp/tag.html', locals())

def searchPage(request):
    song_list = []
    return render(request, 'myapp/searchPage.html', locals())

def GETSearchSong(request):
    if request.method == 'GET':
        key = request.GET.get('search')
        song_list = KkboxSong.objects.filter(song_name__iexact=key)
    return render(request, 'myapp/searchPage.html', locals())

def POSTDeleteSong(request):
    song_list = []
    if request.method == 'POST':
        id = request.POST.get('delete')
        KkboxSong.objects.filter(id=id).delete()
    return render(request, 'myapp/searchPage.html', locals())

def POSTInsertSong(request):
    song_list = []
    if request.method == 'POST':
        song_name = request.POST.get('song_name')
        artist = request.POST.get('artist')
        url = request.POST.get('url')
        image = request.POST.get('image')

        u = KkboxSong.objects.create(song_name=song_name, artist = artist, url = url, image = image,
                                        is_deleted = 0, kkbox_api_id = hash(song_name),
                                        length = 0, created_at = datetime.now(), updated_at = datetime.now())
        u.save()

    return render(request, 'myapp/searchPage.html', locals())

def POSTModifySong(request):
    song_list = []
    if request.method == 'POST':
        id = request.POST.get('id')
        song_name = request.POST.get('song_name')
        artist = request.POST.get('artist')

        # print(id, artist, song_name)
        KkboxSong.objects.filter(id=id).update(song_name=song_name, artist = artist, updated_at = datetime.now())
    return render(request, 'myapp/searchPage.html', locals())


def AndroidLike(request):
    unique_id = set()
    songs = []
    artists = []
    if request.method == 'GET':
        userlike_record = UserlikeRecord.objects.filter(user_like = 'like')
    for rec in userlike_record:
        unique_id.add(rec.item)
    for rec in unique_id:
        re = KkboxSong.objects.get(id=rec.id)
        songs.append(re.song_name)
        artists.append(re.artist)
    return JsonResponse({"song_name":songs, "artist":artists})

    # return JsonResponse({"song_name":songs[0], "artist":artists[0]})
    #return JsonResponse(rejson, safe = False)

def AndroidUnLike(request):
    unique_id = set()
    songs = []
    artists = []
    if request.method == 'GET':
        userlike_record = UserlikeRecord.objects.filter(user_like = 'unlike')
    for rec in userlike_record:
        unique_id.add(rec.item)
    for rec in unique_id:
        re = KkboxSong.objects.get(id=rec.id)
        songs.append(re.song_name)
        artists.append(re.artist)
    return JsonResponse({"song_name":songs, "artist":artists})
