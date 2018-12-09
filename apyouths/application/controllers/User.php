<?php


defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . '/libraries/REST_Controller.php';
require APPPATH . '/libraries/Format.php';

class User extends REST_Controller {

    // Konfigurasi letak folder untuk upload image

    function all_get(){
        $get_user = $this->db->query("
            SELECT
                id_user,
                nama,
                institusi,
                telp,
                email
            FROM user order by id_user asc")->result();
       $this->response(
           array(
               "status" => "success",
               "result" => $get_user
           )
       );
    }

    function all_post() {

        $action  = $this->post('action');
        $data_user = array(
                        'id_user' => $this->post('id_user'),
                        'nama' => $this->post('nama'),
                        'institusi' => $this->post('institusi'),
                        'telp' => $this->post('telp'),
                        'email' => $this->post('email')
                    );

        switch ($action) {
            case 'insert':
                $this->insertUser($data_user);
                break;
            
            case 'update':
                $this->updateUser($data_user);
                break;
            
            case 'delete':
                $this->deleteUser($data_user);
                break;
            
            default:
                $this->response(
                    array(
                        "status"  =>"failed",
                        "message" => "action harus diisi"
                    )
                );
                break;
        }
    }

    function insertUser($data_user){
        if (empty($data_user['nama'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "Nama User harus diisi"
                )
            );
        } else {

            $do_insert = $this->db->insert('user', $data_user);
           
            if ($do_insert){
                $this->response(
                    array(
                        "status" => "success",
                        "result" => array($data_user),
                        "message" => $do_insert
                    )
                );
            }
        }
    }

    function updateUser($data_user){

        // Cek validasi
        if (empty($data_user['nama'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "Nama User harus diisi"
                )
            );
        } else {
            // Cek apakah ada di database
            $get_user_baseID = $this->db->query("
                SELECT 1 id_user
                FROM user
                WHERE id_user =  {$data_user['id_user']}")->num_rows();

            if($get_user_baseID === 0){
                // Jika tidak ada
                $this->response(
                    array(
                        "status"  => "failed",
                        "message" => "ID user tidak ditemukan"
                    )
                );
            } else {
            
              
                    $update = $this->db->query("
                        UPDATE user
                        SET
                        nama    = '{$data_user['nama']}',
                        institusi    = '{$data_user['institusi']}',
                        telp    = '{$data_user['telp']}',
                        email    = '{$data_user['email']}'
                        WHERE id_user = {$data_user['id_user']}"
                    );
               
                if ($update){
                    $this->response(
                        array(
                            "status"    => "success",
                            "result"    => array($data_user),
                            "message"   => $update
                        )
                    );
                }
            }   
        }
    }

    function deleteUser($data_user){

        if (empty($data_user['id_user'])){
            $this->response(
                array(
                    "status" => "failed",
                    "message" => "ID Pembeli harus diisi"
                )
            );
        } else {
            // Cek apakah ada di database
            $get_user_baseID = $this->db->query("
            SELECT 1 id_user
            FROM user
            WHERE id_user =  {$data_user['id_user']}")->num_rows();


            if($get_user_baseID > 0){
                
              
                    $this->db->query("
                        DELETE FROM user
                        WHERE id_user = {$data_user['id_user']}");
                    $this->response(
                        array(
                            "status" => "success",
                            "message" => "Data ID = " .$data_user['id_user']. " berhasil dihapus"
                        )
                    );
                
            
            } else {
                $this->response(
                    array(
                        "status" => "failed",
                        "message" => "ID Pembeli tidak ditemukan"
                    )
                );
            }
        }
    }


}
