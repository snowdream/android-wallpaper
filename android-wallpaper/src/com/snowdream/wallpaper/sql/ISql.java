/*******************************************************************************
 * Copyright (C) 2013 Snowdream Mobile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.snowdream.wallpaper.sql;

import java.sql.SQLException;
import java.util.List;

import com.snowdream.wallpaper.entity.Album;
import com.snowdream.wallpaper.entity.Albums;
import com.snowdream.wallpaper.entity.Image;

/**
 * 
 *
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public interface ISql {
    public void addImage(Image image) throws SQLException;

    public void updateImage(Image image)throws SQLException;

    public Image queryImage(Image image)throws SQLException;
    
    public List<Image> queryImagesFromAlbum(Album album)throws SQLException;

    public void deleteImage(Image image)throws SQLException;

    public void addAlbum(Album album)throws SQLException;

    public void updateAlbum(Album album)throws SQLException;

    public Album queryAlbum(Album album)throws SQLException;

    public void deleteAlbum(Album album)throws SQLException;

    public void addAlbums(Albums albums)throws SQLException;

    public void updateAlbums(Albums albums)throws SQLException;

    public Albums queryAlbums(Albums albums)throws SQLException;

    public void deleteAlbums(Albums albums)throws SQLException;
    
    public List<Image> queryImagesFromAlbums(Albums albums) throws SQLException;
}
